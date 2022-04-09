package kep.winter.amqprabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RabbitTestController {

    private final RabbitTemplate rabbitTemplate;
    public static final String RPC_QUEUE1 = "queue_1";
    public static final String RPC_QUEUE2 = "queue_2";
    public static final String RPC_EXCHANGE = "rpc_exchange";

    @PostMapping("/send/reply/{id}")
    public String asyncSend(@PathVariable String id) {
        rabbitTemplate.convertAndSend(RPC_EXCHANGE, RPC_QUEUE2, "reply", message -> {
            message.getMessageProperties().setCorrelationId(id);
            message.getMessageProperties().setReplyTo("queue_2");
            return message;
        });
        return "sended.";
    }

    @PostMapping("/send/sync")
    public Object syncSend() {
        rabbitTemplate.setUserCorrelationId(true);
        Object o = rabbitTemplate.convertSendAndReceive(RPC_EXCHANGE, RPC_QUEUE1, "Sender's message", m -> {
            m.getMessageProperties().setCorrelationId("ID11231");
            return m;
        });
        log.info("result={}", o);
        return o;
    }




}
