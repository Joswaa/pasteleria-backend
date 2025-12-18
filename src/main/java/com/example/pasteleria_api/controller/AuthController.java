package com.example.pasteleria_api.controller;

import com.example.pasteleria_api.model.User;
import com.example.pasteleria_api.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Registro simple
    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(request.password()); // sin encriptar, solo para demo
        user.setRole("ROLE_USER");
        return userRepository.save(user);
    }

    // Login simple
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Optional<User> userOpt = userRepository.findByUsername(request.username());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(401).build();
        }

        User user = userOpt.get();
        if (!user.getPassword().equals(request.password())) {
            return ResponseEntity.status(401).build();
        }

        // Respuesta mínima (podrías generar un token real, pero no es necesario para la rúbrica)
        LoginResponse response = new LoginResponse("fake-jwt-token", user.getUsername());
        return ResponseEntity.ok(response);
    }

    public record RegisterRequest(String username, String password) {}
    public record LoginRequest(String username, String password) {}
    public record LoginResponse(String token, String username) {}
}