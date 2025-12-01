package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findByNameContainingIgnoreCase(String name);

    List<Restaurant> findByCuisinesContainingIgnoreCase(String cuisine);

    List<Restaurant> findByOpen(boolean open);

    List<Restaurant> findByOwnerId(Long ownerId);
}
