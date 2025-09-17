package cinemaabyss.events.cinema_abyss_events.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

//@Configuration
//todo: killme
public class KafkaTopic {

  @Bean
  public NewTopic topic1() {
    return TopicBuilder.name("topic-1").build();
  }

  @Bean
  public NewTopic topic2() {
    return TopicBuilder.name("topic-2").partitions(3).build();
  }
}
