package com.example.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.RestaurantRequest;
import com.example.backend.model.Restaurant;
import com.example.backend.model.User;
import com.example.backend.repository.RestaurantRepository;
import com.example.backend.service.RestaurantService;
import com.example.backend.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final UserService userService;
    private final RestaurantRepository restaurantRepo;

    public RestaurantController(RestaurantService restaurantService, UserService userService,
            RestaurantRepository restaurantRepo) {
        this.restaurantService = restaurantService;
        this.userService = userService;
        this.restaurantRepo = restaurantRepo;
    }

    @PostMapping("/restaurants")
    public Restaurant addRestaurant(@RequestBody RestaurantRequest req) {

        User owner = userService.getUserById(req.getOwnerId());

        Restaurant r = new Restaurant();
        r.setName(req.getName());
        r.setAddress(req.getAddress());
        r.setCuisines(req.getCuisines());
        r.setRating(req.getRating());
        r.setOpeningHours(req.getOpeningHours());
        r.setOpen(req.isOpen());
        r.setOwner(owner);

        return restaurantService.createRestaurant(r);
    }

    @GetMapping("/restaurant/my")
    public ResponseEntity<?> getMyRestaurant(Authentication authentication) {
        try {
            if (authentication == null) {
                return ResponseEntity.status(401).body("Unauthorized");
            }

            // Get user email from JWT
            String email = authentication.getName();
            User user = userService.getUserByEmail(email);

            if (user == null) {
                return ResponseEntity.status(401).body("User not found");
            }

            // Find restaurant by owner ID
            List<Restaurant> restaurants = restaurantRepo.findByOwnerId(user.getId());

            if (restaurants == null || restaurants.isEmpty()) {
                return ResponseEntity.ok().body(null);
            }

            // Return the first restaurant (owner can manage multiple but we'll show the
            // first one)
            Restaurant r = restaurants.get(0);
            java.util.Map<String, Object> response = new java.util.HashMap<>();
            response.put("id", r.getId());
            response.put("name", r.getName());
            response.put("address", r.getAddress());
            response.put("isOpen", r.isOpen());
            response.put("status", r.getStatus());
            response.put("ownerId", r.getOwner().getId());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getAll() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant getById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }

    @PutMapping("/restaurants/{id}")
    public Restaurant update(@PathVariable Long id, @RequestBody Restaurant newData) {
        return restaurantService.updateRestaurant(id, newData);
    }

    @DeleteMapping("/restaurants/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.ok("Restaurant deleted");
    }
}
