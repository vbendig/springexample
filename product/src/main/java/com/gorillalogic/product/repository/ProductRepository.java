package com.gorillalogic.product.repository;

import com.gorillalogic.product.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findByProductId(long productId);
}
