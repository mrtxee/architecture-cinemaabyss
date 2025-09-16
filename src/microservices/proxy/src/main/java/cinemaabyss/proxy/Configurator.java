package cinemaabyss.proxy;

public final class Configurator {

  public static int PROXY_PORT;
  public static String MONOLITH_URL;
  public static String MOVIES_SERVICE_URL;
  public static String EVENTS_SERVICE_URL;

  static {
    PROXY_PORT = getIntegerEnv("PROXY_PORT", 8000);
    MONOLITH_URL = getStringEnv("MONOLITH_URL", "http://localhost:8080");
    MOVIES_SERVICE_URL = getStringEnv("MOVIES_SERVICE_URL", "http://localhost:8081");
    EVENTS_SERVICE_URL = getStringEnv("EVENTS_SERVICE_URL", "http://localhost:8082");
  }

  private Configurator() {
  }

  private static String getStringEnv(String key, String defaultValue) {
    return System.getenv().getOrDefault(key, defaultValue);
  }

  private static int getIntegerEnv(String key, int defaultValue) {
    try {
      return Integer.parseInt(getStringEnv(key, null));
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

}
