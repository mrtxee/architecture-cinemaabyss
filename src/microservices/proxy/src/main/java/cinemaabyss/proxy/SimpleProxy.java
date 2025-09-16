package cinemaabyss.proxy;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class SimpleProxy {

  private static final String MOVIES_SERVICE = "http://127.0.0.1:8081";
  private static final String USERS_SERVICE = "http://127.0.0.1:8080";
  private static final String PROXY = "http://127.0.0.1:8000";

  private static final HttpClient httpClient = HttpClient.newBuilder()
      .connectTimeout(Duration.ofSeconds(10))
      .build();

  public static void main(String[] args) throws IOException {
    // Создаем HTTP сервер на порту 8000
    HttpServer server = HttpServer.create(
        new InetSocketAddress("127.0.0.1", 8000), 0
    );

    // Обработчик для всех запросов
    server.createContext("/", new ProxyHandler());

    // Запускаем сервер
    server.start();
    System.out.println("Proxy server started on " + PROXY);
    System.out.println("Movies API -> " + MOVIES_SERVICE);
    System.out.println("Users API  -> " + USERS_SERVICE);
  }

  static class ProxyHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
      String method = exchange.getRequestMethod();
      String path = exchange.getRequestURI().getPath();
      String query = exchange.getRequestURI().getQuery();

      System.out.println("Received: " + method + " " + path);

      try {
        String targetUrl;

        // Определяем целевой сервис
        if (path.startsWith("/api/movies")) {
          targetUrl = MOVIES_SERVICE + path + (query != null ? "?" + query : "");
        } else if (path.startsWith("/api/users")) {
          targetUrl = USERS_SERVICE + path + (query != null ? "?" + query : "");
        } else {
          // Для всех других путей возвращаем 404
          sendError(exchange, 404, "Not Found inverse proxy route");
          return;
        }

        // Проксируем запрос
        proxyRequest(exchange, targetUrl);

      } catch (Exception e) {
        System.err.println("Error: " + e.getMessage());
        sendError(exchange, 500, "Internal Server Error");
      }
    }

    private void proxyRequest(HttpExchange exchange, String targetUrl) throws IOException {
      System.out.println("Proxy request: " + targetUrl);
      try {
        // Создаем запрос к целевому сервису
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
            .uri(URI.create(targetUrl))
            .method(
                exchange.getRequestMethod(),
                HttpRequest.BodyPublishers.ofInputStream(() -> exchange.getRequestBody())
            );

        // Копируем заголовки
        exchange.getRequestHeaders().forEach((key, values) -> {
          if (!key.equalsIgnoreCase("Host")) { // Не копируем Host header
            values.forEach(value -> requestBuilder.header(key, value));
          }
        });

        // Выполняем запрос
        HttpResponse<InputStream> response = httpClient.send(
            requestBuilder.build(),
            HttpResponse.BodyHandlers.ofInputStream()
        );

        // Копируем ответ клиенту
        exchange.getResponseHeaders().putAll(response.headers().map());
        exchange.sendResponseHeaders(response.statusCode(), 0);

        try (OutputStream os = exchange.getResponseBody();
            InputStream is = response.body()) {
          is.transferTo(os);
        }

      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        sendError(exchange, 500, "Request interrupted");
      } catch (IOException e) {
        sendError(exchange, 502, "Bad Gateway: " + e.getMessage());
      }
    }

    private void sendError(HttpExchange exchange, int code, String message) throws IOException {
      System.out.println("Error: " + message);
      exchange.sendResponseHeaders(code, message.length());
      try (OutputStream os = exchange.getResponseBody()) {
        os.write(message.getBytes());
      }
    }
  }
}
