package com.example.Clinic_Management_System.controller;

import com.example.Clinic_Management_System.model.Admin;
import com.example.Clinic_Management_System.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admins")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        try {
            List<Admin> admins = adminService.getAllAdmins();
            return new ResponseEntity<>(admins, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable("id") Long adminId) {
        try {
            Optional<Admin> admin = adminService.getAdminById(adminId);
            if (admin.isPresent()) {
                return new ResponseEntity<>(admin.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Admin> getAdminByEmail(@PathVariable("email") String email) {
        try {
            Optional<Admin> admin = adminService.getAdminByEmail(email);
            if (admin.isPresent()) {
                return new ResponseEntity<>(admin.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        try {
            if (adminService.emailExists(admin.getEmail())) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
            Admin createdAdmin = adminService.createAdmin(admin);
            return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable("id") Long adminId, @RequestBody Admin adminDetails) {
        try {
            if (!adminService.adminExists(adminId)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Admin updatedAdmin = adminService.updateAdmin(adminId, adminDetails);
            return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAdmin(@PathVariable("id") Long adminId) {
        try {
            if (!adminService.adminExists(adminId)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            adminService.deleteAdmin(adminId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Admin> login(@RequestParam String email, @RequestParam String password) {
        try {
            Optional<Admin> admin = adminService.authenticate(email, password);
            if (admin.isPresent()) {
                return new ResponseEntity<>(admin.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}