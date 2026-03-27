package com.example.Clinic_Management_System.repository;

import com.example.Clinic_Management_System.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepositary extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
