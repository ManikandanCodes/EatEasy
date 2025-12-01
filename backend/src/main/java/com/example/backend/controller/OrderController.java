package com.example.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.OrderRequest;
import com.example.backend.model.Order;
import com.example.backend.service.OrderService;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order placeOrder(@RequestBody OrderRequest req) {
        Order order = new Order();
        return orderService.placeOrder(req.getUserId(), req.getRestaurantId(), order);
    }

    @GetMapping("/customer/{userId}")
    public List<Order> getOrdersForCustomer(@PathVariable Long userId) {
        return orderService.getOrdersForCustomer(userId);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<Order> getOrdersForRestaurant(@PathVariable Long restaurantId) {
        return orderService.getOrdersForRestaurant(restaurantId);
    }

    @PutMapping("/{orderId}/status/{status}")
    public Order updateStatus(@PathVariable Long orderId, @PathVariable String status) {
        return orderService.updateStatus(orderId, status);
    }
}
