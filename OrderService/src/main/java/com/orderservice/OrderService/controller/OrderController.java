package com.orderservice.OrderService.controller;

import com.orderservice.OrderService.model.Order;
import com.orderservice.OrderService.model.Product;
import com.orderservice.OrderService.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderServices orderServices;

    @GetMapping("/order")
    private Flux<Order> getAllOrders() {
        return orderServices.getAllOrders();
    }
    @GetMapping("/order/{id}")
    private Mono<Order> getOrderById(@PathVariable Long id) {
        return orderServices.getOrderById(id);
    }

    @GetMapping("/product/{id}")
    private Mono<List<Product>> getProductById(@PathVariable Long id) {
        return orderServices.getProductById(id);
    }

    @PostMapping("/order")
    private Mono<Order> addOrders(@RequestBody Order order){
        return orderServices.addOrders(order);
    }

    @PutMapping("/order/{id}")
    private Mono<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        return orderServices.updateOrders(id, order);
    }

    @DeleteMapping("/order/{id}")
    private Mono<ResponseEntity<Order>> deleteOrder(@PathVariable Long id) {
        return orderServices.deleteOrder(id);
    }

}
