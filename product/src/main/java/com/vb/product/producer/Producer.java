package com.gorillalogic.product.producer;

import com.gorillalogic.product.dto.ProductDto;
import com.gorillalogic.product.messaging.MessagingConfiguration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private final RabbitTemplate rabbitTemplate;
    private final MessageConverter jsonMessageConverter;

    public Producer(RabbitTemplate rabbitTemplate, MessageConverter jsonMessageConverter) {
        this.rabbitTemplate = rabbitTemplate;
        this.jsonMessageConverter = jsonMessageConverter;
    }

    public void sendProduct(ProductDto productDto) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.setMessageConverter(jsonMessageConverter);
        rabbitTemplate.convertAndSend(MessagingConfiguration.PRODUCTS_EXCHANGE,
                MessagingConfiguration.PRODUCTS_ROUTING_KEY,
                productDto);
    }

}
