package com.orderservice.OrderService.services;

import com.orderservice.OrderService.model.Order;
import com.orderservice.OrderService.model.Product;
import com.orderservice.OrderService.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class OrderServices {

    private final WebClient webClient;

    public OrderServices(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build();
    }
    @Autowired
    private OrderRepo orderRepo;

    public Flux<Product> getProductById(Long id) {
        return webClient.get()
                .uri("/order-products/{id}",id)
                .retrieve()
                .bodyToFlux(Product.class);
    }
    public Flux<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public Mono<Order> getOrderById(Long id) {
        return orderRepo.getOrderByOrderId(id)
                .map(order -> {
                     order.setProductList(getProductById(order.getProductId()));
                     return order;
                });
    }



    public Mono<Order> addOrders(Order order) {

        return orderRepo.save(order);
    }

    public Mono<Order> updateOrders(Long id, Order order) {

        return orderRepo.getOrderByOrderId(id)
                .flatMap(currentOrder -> {
                    currentOrder.setOrderId(order.getOrderId());
                    currentOrder.setCustomerId(order.getCustomerId());
                    return orderRepo.save(currentOrder);
                        });
    }

    public Mono<ResponseEntity<Order>> deleteOrder(Long id) {
        return orderRepo.deleteById(id)
                .then(Mono.just(new ResponseEntity<Order>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<Order>(HttpStatus.NOT_FOUND));
    }
}
