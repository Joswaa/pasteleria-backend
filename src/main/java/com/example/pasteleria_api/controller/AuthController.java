package com.example.pasteleria_api.controller;

import com.example.pasteleria_api.model.User;
import com.example.pasteleria_api.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setUsername(request.username());
        user.setPassword("{noop}" + request.password()); // simple para el examen
        user.setRole("ROLE_USER");
        return userRepository.save(user);
    }
}