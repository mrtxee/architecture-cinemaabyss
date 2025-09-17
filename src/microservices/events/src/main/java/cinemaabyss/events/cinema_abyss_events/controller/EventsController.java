package cinemaabyss.events.cinema_abyss_events.controller;

import static cinemaabyss.events.cinema_abyss_events.service.kafka.KafkaConstant.USER_TOPIC;

import cinemaabyss.events.cinema_abyss_events.dto.HealthCheck;
import cinemaabyss.events.cinema_abyss_events.dto.MovieResponse;
import cinemaabyss.events.cinema_abyss_events.dto.PaymentResponse;
import cinemaabyss.events.cinema_abyss_events.dto.UserResponse;
import cinemaabyss.events.cinema_abyss_events.service.DataService;
import cinemaabyss.events.cinema_abyss_events.service.kafka.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<HealthCheck> health() {
    return new ResponseEntity<>(new HealthCheck(true), HttpStatus.OK);
  }

  @PostMapping("user")
  public ResponseEntity<UserResponse> sendKafkaUserEvent() {
    kafkaSender.sendMessage("new", USER_TOPIC);
    return new ResponseEntity<>(dataService.getUserResponse(), HttpStatus.CREATED);
  }

  @PostMapping("movie")
  public ResponseEntity<MovieResponse> sendKafkaMovieEvent() {
    return new ResponseEntity<>(dataService.getMovieResponse(), HttpStatus.CREATED);
  }

  @PostMapping("payment")
  public ResponseEntity<PaymentResponse> sendKafkaPaymentEvent() {
    return new ResponseEntity<>(dataService.getPaymentResponse(), HttpStatus.CREATED);
  }

}
