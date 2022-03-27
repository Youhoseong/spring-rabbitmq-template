package kep.winter.amqprabbit;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private static final String EXCHANGE_NAME = "cafe.topic";


    private static final String COFFEE_QUEUE = "coffee.queue";
    private static final String COFFEE_ROUTE_KEY = "order.coffee.#";

    private static final String CAFE_QUEUE = "cafe.queue";
    private static final String CAFE_ROUTE_KEY = "order.cafe.#";


    @Bean
    Queue coffeeQueue() {
        return new Queue(COFFEE_QUEUE, false);
    }

    @Bean
    Binding coffeeBinding(Queue coffeeQueue, TopicExchange exchange) {
        return BindingBuilder.bind(coffeeQueue).to(exchange).with(COFFEE_ROUTE_KEY);
    }

    @Bean
    RabbitTemplate coffeeTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }

    /*
    cafe queue
     */
    @Bean
    Queue cafeQueue() {
        return new Queue(CAFE_QUEUE, false);
    }

    @Bean
    Binding cafeBinding(Queue cafeQueue, TopicExchange exchange) {
        return BindingBuilder.bind(cafeQueue).to(exchange).with(CAFE_ROUTE_KEY);
    }


    @Bean
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

}
