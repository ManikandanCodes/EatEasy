package com.example.backend.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.backend.config.JwtUtil;
import com.example.backend.dto.LoginResponse;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepo, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // ============================
    //          REGISTER
    // ============================
    public User register(User user) {

        if (userRepo.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // If new restaurant owner → mark as NOT registered
        if (user.getRole() == User.Role.RESTAURANT_OWNER) {
            user.setRestaurantRegistered(false); 
        }

        return userRepo.save(user);
    }

    // ============================
    //            LOGIN
    // ============================
    public LoginResponse login(String email, String password) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        // Include restaurant registration status in response
        boolean isRegistered = user.isRestaurantRegistered();

        return new LoginResponse(token, user, isRegistered);
    }
}
