package gmbh.conteco.kakfaspringtutorial;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Scanner;
import java.util.stream.IntStream;

@SpringBootApplication
public class KakfaSpringTutorialApplication {

    public static void main(String[] args) {
        SpringApplication.run(KakfaSpringTutorialApplication.class);
    }

    @KafkaListener(id = "myId", topics = "topic1")
    public void listen(String value) {
        System.out.println(value);
    }

    @Bean
    public CommandLineRunner runner(KafkaTemplate<String, String> template) {
        return args -> {
            IntStream.range(0, 100)
                    .forEach(i -> template.send("topic1", String.valueOf(i)));
        };
    }
}
