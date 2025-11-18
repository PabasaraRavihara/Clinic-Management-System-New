package com.example.Clinic_Management_System.controller;

import com.example.Clinic_Management_System.model.Appointment;
import com.example.Clinic_Management_System.repository.DoctorRepositary;
import com.example.Clinic_Management_System.model.Doctor;
import com.example.Clinic_Management_System.service.AppointmentService;
import com.example.Clinic_Management_System.service.DoctorService;
import com.example.Clinic_Management_System.service.impl.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "*")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // Create Doctor
    @PostMapping
    public ResponseEntity<Doctor> saveDoctor(@RequestBody Doctor doctor) {
        Doctor savedDoctor = doctorService.saveDoctor(doctor);
        return new
                ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
    }

    // Get All Doctors
    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String specialization
    )

     {
            if (name != null) {
                return ResponseEntity.ok(Collections.singletonList(doctorService.searchByName(name)));
            } else if (specialization != null) {
                return ResponseEntity.ok(Collections.singletonList(doctorService.filterBySpecialization(specialization)));
            }else {
                return ResponseEntity.ok(doctorService.getAllDoctors());
                }
    }

    // Get Doctor by ID
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable("id") Long id) {
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    // Update Doctor
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable("id") Long id, @RequestBody Doctor doctor) {
        Doctor existingDoctor = doctorService.updateDoctor(doctor,id);
        if (existingDoctor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existingDoctor.setName(doctor.getName());
        existingDoctor.setSpecialization(doctor.getSpecialization());
        Doctor updatedDoctor = doctorService.saveDoctor(existingDoctor);
        return new ResponseEntity<>(updatedDoctor, HttpStatus.OK);
    }

    // Delete Doctor
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable("id") Long id) {
        if ( doctorService.deleteDoctor(id)) {
            return new ResponseEntity<>("Doctor not found.", HttpStatus.NOT_FOUND);
        }
        doctorService.deleteDoctor(id);
        return new ResponseEntity<>("Doctor deleted successfully.", HttpStatus.OK);
    }
    // 📅 Get Doctor Schedule
    @GetMapping("/{id}/Appointment")
    public ResponseEntity<List<Appointment>> getSchedule(@PathVariable Long id) {
        return ResponseEntity.ok(AppointmentService.getAppointmentByDoctor(id));
    }
    // ➕ Add Schedule
    @PostMapping("/{id}/Appointment")
    public ResponseEntity<Appointment> addSchedule(@PathVariable Long id, @RequestBody Appointment schedule) {
        return new ResponseEntity<>(AppointmentService.addSchedule(id, schedule), HttpStatus.CREATED);
    }
    // ❌ Delete Schedule
    @DeleteMapping("/Appointment/{AppointmentId}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Long scheduleId) {
        AppointmentService.(scheduleId);
        return ResponseEntity.ok("Schedule deleted successfully");
    }
}
