package com.ecommerce.productservice.services;

import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    public Flux<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Mono<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Mono<Product> addProduct(Product product) {

        return productRepository.save(product);
    }

    public Mono<ResponseEntity<Product>> updateProduct(Product product, Long id) {

        return productRepository.findById(id)
                .flatMap(currentProduct -> {
                    currentProduct.setPrice(product.getPrice());
                    currentProduct.setDescription(product.getDescription());

                    return productRepository.save(currentProduct);
                })
                .map(updateProduct -> new ResponseEntity<>(updateProduct,HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.OK));
    }

    public Mono<ResponseEntity<Void>> deleteProduct(Long id) {

        return productRepository.deleteById(id)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    public Flux<Product> getProductByProductId(Long id) {
        return productRepository.findProductByProductId(id);

    }
}
