package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProductController {
    @Autowired
    private ProductServices productServices;


    @GetMapping("/product")
    public Flux<Product> getAllProduct() {
        return productServices.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public Mono<Product> getProductById(@PathVariable Long id) {
        return productServices.getProductById(id);
    }

    @GetMapping("/order-products/{id}")
    public Flux<Product> getProductByProductId(@PathVariable Long id) {
        return productServices.getProductByProductId(id);
    }

    @PostMapping("/product")
    public Mono<Product> addProduct(@RequestBody Product product) {
        return productServices.addProduct(product);
    }

    @PutMapping("/product/{id}")
    public Mono<ResponseEntity<Product>> updateProduct(@RequestBody Product product, @PathVariable Long id){
        return productServices.updateProduct(product,id);
    }

    @DeleteMapping("/product/{id}")
    public Mono<ResponseEntity<Void>> deleteProduct(@PathVariable Long id) {
        return productServices.deleteProduct(id);
    }

}
