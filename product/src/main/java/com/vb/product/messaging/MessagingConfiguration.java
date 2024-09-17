package com.gorillalogic.product.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfiguration {
    public static final String PRODUCTS_EXCHANGE = "products-exchange";
    public static final String PRODUCTS_ROUTING_KEY = "foo.bar.baz";
    public static final String PRODUCT_QUEUE = "product-queue";

    @Bean
    Queue queue() {
        return new Queue(PRODUCT_QUEUE, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(PRODUCTS_EXCHANGE);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(PRODUCTS_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

}
