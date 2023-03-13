package com.gorillalogic.product.consumer;

import com.gorillalogic.product.dto.ProductDto;
import com.gorillalogic.product.messaging.MessagingConfiguration;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @RabbitListener(queues = MessagingConfiguration.PRODUCT_QUEUE)
    public void receiveMessage(ProductDto productDto) {
        System.out.println("Received <" + productDto + ">");
    }

}