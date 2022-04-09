package kep.winter.amqprabbit;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CafeMessageListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "coffee.queue")
    public String receiveMessage(final Message message) {
        log.info("message={}", new String(message.getBody()));
        return "reply";
    }



}
