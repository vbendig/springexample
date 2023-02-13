package com.gorillalogic.product.controller;

import com.gorillalogic.product.model.Product;
import com.gorillalogic.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController()
@RequestMapping("api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(path = "/add")
    public void saveProduct(@RequestBody Product product) {

    }
    @GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable long id) {
        log.info("Getting req for id" + id);
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping(path="/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProduct0(@PathVariable long id) {
        return productService.getProductById(id);
    }

    @GetMapping(path="/get1/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Product getProduct1(@PathVariable long id) {
        return productService.getProductById(id);
    }

    @GetMapping(path="/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProduct() {
        return productService.getAll();
    }

}
