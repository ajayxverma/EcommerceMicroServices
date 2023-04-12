package com.orderservice.OrderService.repository;

import com.orderservice.OrderService.model.Order;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface OrderRepo extends R2dbcRepository<Order, Long> {

    @Query("select * from orders where order_id = :id")
    public Mono<Order> getOrderByOrderId(Long id);
}
