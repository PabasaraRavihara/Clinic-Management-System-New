package com.example.Clinic_Management_System.service.impl;
import com.example.Clinic_Management_System.model.Doctor;
import com.example.Clinic_Management_System.repository.DoctorRepositary;
import com.example.Clinic_Management_System.service.DoctorService;
//import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService{
    @Autowired
    private DoctorRepositary doctorRepositary;

    // Save doctor in the database
    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepositary.save(doctor);
    }

    // Get all doctors from the database
    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepositary.findAll();
    }

    // Get doctor by ID
    @Override
    public Doctor getDoctorById(long id) {
        return doctorRepositary.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
    }

    // Update doctor
    @Override
    public Doctor updateDoctor( Doctor doctor, long id) {
        Doctor existingDoctor = doctorRepositary.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));

        existingDoctor.setName(doctor.getName());
        existingDoctor.setSpecialization(doctor.getSpecialization());
        existingDoctor.setEmail(doctor.getEmail());
        existingDoctor.setPhone(doctor.getPhone());

        doctorRepositary.save(existingDoctor);
        return existingDoctor;
    }

    // Delete doctor
    @Override
    public boolean deleteDoctor(long id) {
        doctorRepositary.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));

        doctorRepositary.deleteById(id);
        return false;
    }
    @Override
    public Doctor searchByName(String name) {
        return doctorRepositary.findByNameContainingIgnoreCase(name);
    }
    @Override
    public Doctor filterBySpecialization(String specialization) {
        return doctorRepositary.findBySpecialization(specialization);
    }

    @Override
    public Optional<Object> findById(Long doctorId) {
        return Optional.empty();
    }
}
