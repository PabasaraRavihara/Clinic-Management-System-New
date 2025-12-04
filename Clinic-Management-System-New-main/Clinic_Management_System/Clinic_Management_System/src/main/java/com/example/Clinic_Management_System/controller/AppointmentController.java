package com.example.Clinic_Management_System.controller;

import com.example.Clinic_Management_System.model.Appointment;
import com.example.Clinic_Management_System.model.Doctor;
import com.example.Clinic_Management_System.model.Patient;
import com.example.Clinic_Management_System.service.AppointmentService;
import com.example.Clinic_Management_System.service.DoctorService;
import com.example.Clinic_Management_System.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*") // Note: SecurityConfig handles CORS now, but keeping this is okay
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    // ✅ Create Appointment (Improved)
    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody Map<String, String> body) {
        try {
            // 1. IDs 
            Long patientId = Long.valueOf(body.get("patientId"));
            Long doctorId = Long.valueOf(body.get("doctorId"));

            // 2. Patient and Doctor searching
            Patient patient = patientService.findById(patientId).orElse(null);
            Doctor doctor = (Doctor) doctorService.findById(doctorId).orElse(null);

            // 3.  Error 
            if (patient == null || doctor == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error: Patient ID or Doctor ID not found in database.");
            }

            // 4.create Appointment 
            Appointment appointment = new Appointment();
            appointment.setPatient(patient);
            appointment.setDoctor(doctor);
            
            // Date & Time conversion
            if (body.get("appointmentTime") != null) {
                appointment.setAppointmentTime(LocalDateTime.parse(body.get("appointmentTime")));
            }
            if (body.get("date") != null) {
                appointment.setDate(LocalDate.parse(body.get("date")));
            }
            if (body.get("time") != null) {
                appointment.setTime(LocalTime.parse(body.get("time")));
            }

            appointment.setStatus(body.get("status"));
            appointment.setNotes(body.get("notes"));

            Appointment saved = appointmentService.saveAppointment(appointment);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);

        } catch (Exception e) {
            e.printStackTrace(); //  Error  Console 
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    // Get All, Get By ID, Update, Delete methods...
    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }
}