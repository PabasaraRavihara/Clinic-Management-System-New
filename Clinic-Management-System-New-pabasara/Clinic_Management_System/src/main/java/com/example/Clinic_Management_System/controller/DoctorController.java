package com.example.Clinic_Management_System.controller;
import com.example.Clinic_Management_System.model.Appointment;
import com.example.Clinic_Management_System.model.Doctor;
import com.example.Clinic_Management_System.service.AppointmentService;
import com.example.Clinic_Management_System.service.DoctorService;
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

    @Autowired
    private AppointmentService appointmentService;

    // Create Doctor
    @PostMapping
    public ResponseEntity<Doctor> saveDoctor(@RequestBody Doctor doctor) {
        Doctor savedDoctor = doctorService.saveDoctor(doctor);
        return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
    }

    // Get All Doctors
    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String specialization
    ) {
        if (name != null) {
            return ResponseEntity.ok(Collections.singletonList(doctorService.searchByName(name)));
        } else if (specialization != null) {
            return ResponseEntity.ok(Collections.singletonList(doctorService.filterBySpecialization(specialization)));
        } else {
            return ResponseEntity.ok(doctorService.getAllDoctors());
        }
    }

    // Get Doctor by ID
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    // Update Doctor
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        Doctor updatedDoctor = doctorService.updateDoctor(doctor, id);
        if (updatedDoctor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedDoctor, HttpStatus.OK);
    }

    // Delete Doctor
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
        boolean deleted = doctorService.deleteDoctor(id);
        if (!deleted) {
            return new ResponseEntity<>("Doctor not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Doctor deleted successfully.", HttpStatus.OK);
    }

    // Get Appointments (Schedule) by Doctor ID
    @GetMapping("/{id}/appointments")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctor(@PathVariable Long id) {
        List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(id);
        return ResponseEntity.ok(appointments);
    }

    // Add Appointment (Schedule) for a Doctor
    @PostMapping("/{id}/appointments")
    public ResponseEntity<Appointment> addAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        Appointment created = appointmentService.addAppointment(id, appointment);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Delete Appointment
    @DeleteMapping("/appointments/{appointmentId}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long appointmentId) {
        boolean deleted= appointmentService.deleteAppointment(appointmentId);
        if (!deleted) {
            return new ResponseEntity<>("Appointment not found.", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Appointment deleted successfully");
    }
}
