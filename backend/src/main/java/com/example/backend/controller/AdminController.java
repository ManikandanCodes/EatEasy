package com.example.backend.controller;

import com.example.backend.model.User;
import com.example.backend.model.Restaurant;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.RestaurantRepository;
import com.example.backend.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderRepository orderRepository;

    public AdminController(
            UserRepository userRepository,
            RestaurantRepository restaurantRepository,
            OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderRepository = orderRepository;
    }

    // Dashboard Statistics
    @GetMapping("/stats")
    public ResponseEntity<?> getAdminStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsers", userRepository.count());
        stats.put("totalRestaurants", restaurantRepository.count());
        stats.put("pendingRestaurants", 0); // Simple for now
        stats.put("totalOrders", orderRepository.count());
        stats.put("todayOrders", 0);
        stats.put("todayRevenue", 0);
        return ResponseEntity.ok(stats);
    }

    // Get All Restaurants
    @GetMapping("/restaurants")
    public ResponseEntity<?> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("restaurants", restaurants);
        return ResponseEntity.ok(response);
    }

    // Approve Restaurant
    @PutMapping("/restaurants/{id}/approve")
    public ResponseEntity<?> approveRestaurant(@PathVariable Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        restaurant.setStatus(Restaurant.ApprovalStatus.APPROVED);
        restaurantRepository.save(restaurant);
        return ResponseEntity.ok(Map.of("message", "Approved"));
    }

    // Block Restaurant
    @PutMapping("/restaurants/{id}/block")
    public ResponseEntity<?> blockRestaurant(@PathVariable Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        restaurant.setStatus(Restaurant.ApprovalStatus.REJECTED);
        restaurantRepository.save(restaurant);
        return ResponseEntity.ok(Map.of("message", "Blocked"));
    }

    // Activate Restaurant
    @PutMapping("/restaurants/{id}/activate")
    public ResponseEntity<?> activateRestaurant(@PathVariable Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        restaurant.setStatus(Restaurant.ApprovalStatus.APPROVED);
        restaurantRepository.save(restaurant);
        return ResponseEntity.ok(Map.of("message", "Activated"));
    }

    // Get All Users
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    // Block User
    @PutMapping("/users/{id}/block")
    public ResponseEntity<?> blockUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setActive(false);
        userRepository.save(user);
        return ResponseEntity.ok(Map.of("message", "User blocked"));
    }

    // Activate User
    @PutMapping("/users/{id}/activate")
    public ResponseEntity<?> activateUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setActive(true);
        userRepository.save(user);
        return ResponseEntity.ok(Map.of("message", "User activated"));
    }

    // Analytics (simple)
    @GetMapping("/analytics")
    public ResponseEntity<?> getAnalytics() {
        Map<String, Object> analytics = new HashMap<>();
        analytics.put("totalOrders", orderRepository.count());
        analytics.put("completedOrders", 0);
        analytics.put("cancelledOrders", 0);
        analytics.put("totalRevenue", 0);
        analytics.put("topRestaurants", List.of());
        analytics.put("monthlyStats", List.of());
        return ResponseEntity.ok(analytics);
    }

    // Coupons (simple placeholders)
    @GetMapping("/coupons")
    public ResponseEntity<?> getAllCoupons() {
        return ResponseEntity.ok(List.of());
    }

    @PostMapping("/coupons")
    public ResponseEntity<?> createCoupon(@RequestBody Map<String, Object> data) {
        return ResponseEntity.ok(Map.of("message", "Coupon created"));
    }

    @DeleteMapping("/coupons/{id}")
    public ResponseEntity<?> deleteCoupon(@PathVariable Long id) {
        return ResponseEntity.ok(Map.of("message", "Coupon deleted"));
    }
}
