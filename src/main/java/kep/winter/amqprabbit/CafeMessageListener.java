package kep.winter.amqprabbit;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CafeMessageListener {

    @RabbitListener(queues = "coffee.queue")
    public void receiveMessage(final Message message) {
        log.info("message={}", new String(message.getBody()));
    }
}
