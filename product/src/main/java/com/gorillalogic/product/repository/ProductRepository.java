package com.gorillalogic.product.repository;

import com.gorillalogic.product.model.Product;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>, QuerydslPredicateExecutor<Product>{
    Product findByProductId(long productId);

}
