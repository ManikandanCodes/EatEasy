package com.example.backend.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date orderTime;
    private String status; // RECEIVED, PREPARING, READY, OUT_FOR_DELIVERY, COMPLETED, CANCELLED

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    // GETTERS SETTERS
    public Long getId() { return id; }
    public Date getOrderTime() { return orderTime; }
    public String getStatus() { return status; }
    public User getCustomer() { return customer; }
    public Restaurant getRestaurant() { return restaurant; }
    public List<OrderItem> getItems() { return items; }

    public void setId(Long id) { this.id = id; }
    public void setOrderTime(Date orderTime) { this.orderTime = orderTime; }
    public void setStatus(String status) { this.status = status; }
    public void setCustomer(User customer) { this.customer = customer; }
    public void setRestaurant(Restaurant restaurant) { this.restaurant = restaurant; }
    public void setItems(List<OrderItem> items) { this.items = items; }
}
