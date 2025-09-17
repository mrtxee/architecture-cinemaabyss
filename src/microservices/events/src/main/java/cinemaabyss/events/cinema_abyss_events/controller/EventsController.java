package cinemaabyss.events.cinema_abyss_events.controller;

import static cinemaabyss.events.cinema_abyss_events.service.kafka.KafkaConstant.USER_TOPIC;

import cinemaabyss.events.cinema_abyss_events.dto.HealthCheck;
import cinemaabyss.events.cinema_abyss_events.dto.MovieResponse;
import cinemaabyss.events.cinema_abyss_events.dto.PaymentResponse;
import cinemaabyss.events.cinema_abyss_events.dto.UserResponse;
import cinemaabyss.events.cinema_abyss_events.service.DataService;
import cinemaabyss.events.cinema_abyss_events.service.kafka.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/events/")
public class EventsController {

  @Autowired
  private KafkaSender kafkaSender;
  @Autowired
  private DataService dataService;

  @GetMapping("health")
  public HealthCheck health() {
    return new HealthCheck(true);
  }

  @PostMapping("user")
  public UserResponse sendKafkaUserEvent() {
    kafkaSender.sendMessage("new", USER_TOPIC);
    return dataService.getUserResponse();
  }

  @PostMapping("movie")
  public MovieResponse sendKafkaMovieEvent() {
    return dataService.getMovieResponse();
  }

  @PostMapping("payment")
  public PaymentResponse sendKafkaPaymentEvent() {
    return dataService.getPaymentResponse();
  }

}
