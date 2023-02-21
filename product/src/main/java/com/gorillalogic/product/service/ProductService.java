package com.gorillalogic.product.service;

import com.gorillalogic.product.dto.ProductDto;
import com.gorillalogic.product.mapper.ProductMapper;
import com.gorillalogic.product.model.Product;
import com.gorillalogic.product.model.QProduct;
import com.gorillalogic.product.repository.ProductRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@Repository
public class ProductService {
    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDto> getAll() {
        List<Product> products = (List<Product>) productRepository.findAll();
        return products.stream().map(productMapper::productToProductDto).toList();
    }

    public ProductDto getProductById(long productId) {
        log.info("Getting req for productId " + productId);
        Product product = productRepository.findByProductId(productId);
        return productMapper.productToProductDto(product);
    }

    @Transactional
    public ProductDto saveProduct(Product product) {
        boolean doesProdExist = productExists(product);
        doesProdExist &= productExists1(product);
        doesProdExist &= productExists2(product);
        if (!doesProdExist) {
            product = productRepository.save(product);
            return productMapper.productToProductDto(product);
        } else {
            throw new IllegalArgumentException("Product already exists");
        }
    }

    public boolean productExists(Product product) {
        QProduct qProduct = QProduct.product;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qProduct.name.eq(product.getName()))
                        .and(qProduct.description.eq(product.getDescription()));
        return productRepository.count(builder) > 0;
    }

    public boolean productExists1(Product product) {
        JPAQueryFactory factory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;
        return factory
                .selectFrom(qProduct)
                .where(qProduct.name.eq(product.getName()), qProduct.description.eq(product.getDescription()))
                .stream().findAny().isPresent();

    }

    public boolean productExists2(Product product) {
        QProduct qProduct = QProduct.product;
        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(qProduct.name.eq(product.getName()))
                .and(qProduct.description.eq(product.getDescription()));
        return productRepository.exists(predicate);
    }

    public void deleteProduct(long productId) {
        Product product = productRepository.findByProductId(productId);
        if (product != null) {
            productRepository.delete(product);
        }
    }
}
