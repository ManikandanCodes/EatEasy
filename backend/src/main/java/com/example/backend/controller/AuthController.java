package com.example.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.dto.LoginRequest;
import com.example.backend.dto.LoginResponse;
import com.example.backend.dto.RegisterRequest;
import com.example.backend.model.User;
import com.example.backend.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // ============================
    //      REGISTER USER
    // ============================
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {

        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        user.setPassword(req.getPassword());
        user.setRole(User.Role.valueOf(req.getRole()));

        // 🔥 IMPORTANT: Restaurant owners must register restaurant after signup
        if (req.getRole().equalsIgnoreCase("RESTAURANT_OWNER")) {
            user.setRestaurantRegistered(false);
        }

        return ResponseEntity.ok(authService.register(user));
    }

    // ============================
    //         LOGIN USER
    // ============================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {

        LoginResponse response =
                authService.login(req.getEmail(), req.getPassword());

        return ResponseEntity.ok(response);
    }
}
