package kep.winter.amqprabbit;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageListener {

    public static final String RPC_QUEUE1 = "queue_1";
    public static final String RPC_QUEUE2 = "queue_2";
    public static final String RPC_EXCHANGE = "rpc_exchange";

    @RabbitListener(queues = RPC_QUEUE1)
    public void receiveMessage(final Message message) {
        log.info("message={}", new String(message.getBody()));

        MessageProperties messageProperties = message.getMessageProperties();
        String correlationId = messageProperties.getCorrelationId();
        String replyTo = messageProperties.getReplyTo();

        log.info("correlationId={}, replyTo={}", correlationId, replyTo);

        //return "reply";
    }



}
