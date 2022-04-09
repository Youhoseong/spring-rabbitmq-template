package kep.winter.amqprabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HealthCheckController {

    private final RabbitTemplate rabbitTemplate;
    private static final String EXCHANGE_NAME = "cafe.topic";

    @PostMapping("/health-check")
    public String asyncSend() {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "order.coffee.1", "It is ok?");
        return "sended.";
    }

    @PostMapping("/health-check/rpc")
    public Object syncSend() {
        rabbitTemplate.setReplyTimeout(60000);

        Object o = rabbitTemplate.convertSendAndReceive(EXCHANGE_NAME, "order.coffee.1", "It is OKOK?", message -> {
          //  message.getMessageProperties().setReplyTo("reply.queue");
           // message.getMessageProperties().setCorrelationId("ID11231");
            return message;
        });

        log.info("result={}", o);
        return o;
    }



}
