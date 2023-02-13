package com.gorillalogic.product.service;

import com.gorillalogic.product.model.Product;
import com.gorillalogic.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return (List<Product>) productRepository.findAll();
    }

    public Product getProductById(long productId) {
        log.info("Getting req for productId " + productId);
        return productRepository.findByProductId(productId);
    }
}
