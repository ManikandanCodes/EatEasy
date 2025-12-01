package com.example.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.model.Restaurant;
import com.example.backend.repository.RestaurantRepository;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepo;

    public RestaurantService(RestaurantRepository restaurantRepo) {
        this.restaurantRepo = restaurantRepo;
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepo.save(restaurant);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepo.findAll();
    }

    public Restaurant getRestaurantById(Long id) {
        return restaurantRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }

    public List<Restaurant> searchByName(String name) {
        return restaurantRepo.findByNameContainingIgnoreCase(name);
    }

    public List<Restaurant> searchByCuisine(String cuisine) {
        return restaurantRepo.findByCuisinesContainingIgnoreCase(cuisine);
    }

    public List<Restaurant> getOpenRestaurants() {
        return restaurantRepo.findByOpen(true);
    }

    public Restaurant updateRestaurant(Long id, Restaurant updated) {
        Restaurant restaurant = getRestaurantById(id);

        restaurant.setName(updated.getName());
        restaurant.setAddress(updated.getAddress());
        restaurant.setCuisines(updated.getCuisines());
        restaurant.setRating(updated.getRating());
        restaurant.setOpeningHours(updated.getOpeningHours());
        restaurant.setOpen(updated.isOpen());

        return restaurantRepo.save(restaurant);
    }

    public void deleteRestaurant(Long id) {
        restaurantRepo.deleteById(id);
    }
}
