package cinemaabyss.events.cinema_abyss_events.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
    scanBasePackages = {
        "cinemaabyss.events.cinema_abyss_events"
    }
)
public class CinemaAbyssEventsApplication {

  public static void main(String[] args) {
    SpringApplication.run(CinemaAbyssEventsApplication.class, args);
  }

}
