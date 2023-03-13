package com.gorillalogic.product.controller;

import com.gorillalogic.product.dto.ProductDto;
import com.gorillalogic.product.producer.Producer;
import com.gorillalogic.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@Slf4j
public class MessageController {
    Producer producer;
    ProductService productService;

    public MessageController (Producer producer, ProductService productService) {
        this.producer = producer;
        this.productService = productService;
    }

    @GetMapping("/send-message/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void sendMessage(@PathVariable Long id) {
        try {
            ProductDto product = productService.getProductById(id);
            producer.sendProduct(product);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
