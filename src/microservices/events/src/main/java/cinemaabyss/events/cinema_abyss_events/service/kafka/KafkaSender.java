package cinemaabyss.events.cinema_abyss_events.service.kafka;

import cinemaabyss.events.cinema_abyss_events.dto.UserDto;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

//@Slf4j
@Component
public class KafkaSender {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage(String message, String topicName) {
    CompletableFuture<SendResult<String, String>> sent = kafkaTemplate.send(topicName, message);
  }
}
