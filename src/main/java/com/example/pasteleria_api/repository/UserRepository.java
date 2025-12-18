package com.example.pasteleria_api.repository;

import com.example.pasteleria_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}