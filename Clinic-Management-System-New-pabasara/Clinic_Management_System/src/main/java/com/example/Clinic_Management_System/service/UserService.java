package com.example.Clinic_Management_System.service;

import com.example.Clinic_Management_System.model.User;

public interface UserService {
    User register(User user);
    User getByEmail(String email);
    boolean checkPassword(String raw, String enc);
}
