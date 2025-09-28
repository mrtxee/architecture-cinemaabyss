package cinemaabyss.events.cinema_abyss_events.controller;

import cinemaabyss.events.cinema_abyss_events.dto.HealthCheck;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class KubernetesController {

  @GetMapping({"health", "liveness", "readiness", "startup"})
  public ResponseEntity<HealthCheck> health() {
    return new ResponseEntity<>(new HealthCheck(true), HttpStatus.OK);
  }

}
