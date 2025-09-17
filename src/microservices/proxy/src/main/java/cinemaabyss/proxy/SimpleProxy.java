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

  private static final HttpClient httpClient = HttpClient.newBuilder()
      .connectTimeout(Duration.ofSeconds(10))
      .build();

  public static void main(String[] args) throws IOException {
    HttpServer server = HttpServer.create(
        new InetSocketAddress("0.0.0.0", Configurator.PROXY_PORT), 0
    );

    // Обработчик для всех запросов
    server.createContext("/", new ProxyHandler());

    // Запускаем сервер
    server.start();
    System.out.println("Proxy server started on " + Configurator.PROXY_PORT);
    System.out.println("Movies API -> " + Configurator.MOVIES_SERVICE_URL);
    System.out.println("Users API  -> " + Configurator.MONOLITH_URL);
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

        if (path.startsWith("/api/movies")) {
          targetUrl = Configurator.MOVIES_SERVICE_URL + path + (query != null ? "?" + query : "");
        } else if (path.startsWith("/api/users") || path.startsWith("/health")) {
          targetUrl = Configurator.MONOLITH_URL + path + (query != null ? "?" + query : "");
        } else {
          sendError(exchange, 404, "Not Found");
          return;
        }

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
          System.out.println("header: " + key + " : " + values);
          // fetch new Host header
          if (!key.equalsIgnoreCase("Host") && !key.equalsIgnoreCase("Connection")) {
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
