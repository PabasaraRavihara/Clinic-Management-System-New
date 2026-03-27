package com.example.Clinic_Management_System.service.impl;

import com.example.Clinic_Management_System.model.User;
import com.example.Clinic_Management_System.repository.UserRepositary;
import com.example.Clinic_Management_System.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private  UserRepositary repo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    @Override
    public User getByEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }

    @Override
    public boolean checkPassword(String raw, String enc) {
        return encoder.matches(raw, enc);
    }
}
