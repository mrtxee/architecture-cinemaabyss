package cinemaabyss.events.cinema_abyss_events.service;

import cinemaabyss.events.cinema_abyss_events.dto.MovieResponse;
import cinemaabyss.events.cinema_abyss_events.dto.PaymentResponse;
import cinemaabyss.events.cinema_abyss_events.dto.UserResponse;
import java.time.Instant;
import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class DataService {

  private final static Random RAND = new Random();

  public UserResponse getUserResponse() {
    return new UserResponse(
        getNum(), "username", "action", Instant.now(), "success"
    );
  }

  public MovieResponse getMovieResponse() {
    return new MovieResponse(
        getNum(), "Test Movie Event", "viewed", getNum(), "success"
    );
  }

  public PaymentResponse getPaymentResponse() {
    return new PaymentResponse(
        getNum(), getNum(), Float.valueOf(getNum()),  "success", Instant.now(), "credit_card"
    );
  }

  private int getNum(){
    return RAND.nextInt(1,500);
  }

}
