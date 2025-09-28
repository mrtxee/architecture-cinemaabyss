package cinemaabyss.events.cinema_abyss_events.service.kafka;

import static cinemaabyss.events.cinema_abyss_events.service.kafka.KafkaConstant.GROUP_ID;
import static cinemaabyss.events.cinema_abyss_events.service.kafka.KafkaConstant.MOVIE_TOPIC;
import static cinemaabyss.events.cinema_abyss_events.service.kafka.KafkaConstant.PAYMENT_TOPIC;
import static cinemaabyss.events.cinema_abyss_events.service.kafka.KafkaConstant.USER_TOPIC;

import cinemaabyss.events.cinema_abyss_events.entity.User;
import cinemaabyss.events.cinema_abyss_events.repository.MovieRepository;
import cinemaabyss.events.cinema_abyss_events.repository.PaymentRepository;
import cinemaabyss.events.cinema_abyss_events.repository.UserRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

  @KafkaListener(topics = USER_TOPIC, groupId = GROUP_ID)
  void userEventListener(String data) {
    User user = new User();
    String email = UUID.randomUUID().toString().substring(20);
    String name = UUID.randomUUID().toString().substring(14);
    user.setEmail(email);
    user.setUsername(name);
    System.out.printf("Saved %s: %s %n", data, user);
  }

  @KafkaListener(topics = MOVIE_TOPIC, groupId = GROUP_ID)
  void movieEventListener(String data) {
    System.out.printf("Saved %s: %s %n", data);
  }

  @KafkaListener(topics = PAYMENT_TOPIC, groupId = GROUP_ID)
  void paymentEventListener(String data) {
    System.out.printf("Saved %s: %s %n", data);
  }

}
