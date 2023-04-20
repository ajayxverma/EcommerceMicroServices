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
    @Autowired
    private WebClient.Builder webClientBuilder;

    /*public OrderServices(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl("http://localhost:8082").build();
    }*/

    private String getProductByIdUri = "http://product-service/order-products/{id}";
    @Autowired
    private OrderRepo orderRepo;

    public OrderServices(WebClient.Builder webClientBuilder, OrderRepo orderRepo) {
        this.webClientBuilder = webClientBuilder;
        this.orderRepo = orderRepo;
    }

    public Mono<List<Product>> getProductById(Long id) {
        return webClientBuilder.build()
                .get()
                .uri(getProductByIdUri,id)
                .retrieve()
                .bodyToFlux(Product.class)
                .collectList();
    }
    public Flux<Order> getAllOrders() {
        return orderRepo.findAll()
                .flatMap(order -> getProductById(order.getProductId())
                        .map(products -> {
                            order.setProductList(products);
                            return order;
                        }));
    }

    public Mono<Order> getOrderById(Long id) {
         var product = orderRepo.getOrderByOrderId(id)
                .flatMap(order ->
                        getProductById(order.getProductId())
                        .map(productList -> {
                            order.setProductList(productList);
                            return order;
                        }));
         return  product;
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
