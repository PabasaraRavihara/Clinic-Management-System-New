package com.example.Clinic_Management_System.controller;

import com.example.Clinic_Management_System.config.JwtUtil;
import com.example.Clinic_Management_System.model.User;
import com.example.Clinic_Management_System.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private   UserService userService;
    private   JwtUtil jwtUtil;


    // -------------------- REGISTER --------------------
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    // -------------------- LOGIN --------------------
    @PostMapping("/login")
    public String login(@RequestBody User req) {
        User user = userService.getByEmail(req.getEmail());
        if (user == null || !userService.checkPassword(req.getPassword(), user.getPassword())) {
            return "Invalid Credentials";
        }
        return jwtUtil.generateToken(user.getEmail(), user.getRole());
    }

    // -------------------- ROLE-BASED EXAMPLES --------------------
    @GetMapping("/admin")
    public String adminAccess() {
        return "Admin Access Granted";
    }

    @GetMapping("/doctor")
    public String doctorAccess() {
        return "Doctor Access Granted";
    }

    @GetMapping("/patient")
    public String patientAccess() {
        return "Patient Access Granted";
    }
}
