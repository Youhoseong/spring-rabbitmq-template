package kep.winter.amqprabbit;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HealthCheckController {

    private final RabbitTemplate rabbitTemplate;
    private static final String EXCHANGE_NAME = "cafe.topic";

    @PostMapping("/health-check")
    public String testMessagePublish() {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "", "It is ok?");
        return "sended.";
    }
}
