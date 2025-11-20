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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    // ✅ Create Appointment
    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Map<String, String> body) {
        try {
            Long patientId = Long.valueOf(body.get("patientId"));
            Long doctorId = Long.valueOf(body.get("doctorId"));
            LocalDateTime time = LocalDateTime.parse(body.get("appointmentTime"));

            Patient patient = patientService.findById(patientId).orElse(null);
            Doctor doctor = (Doctor) doctorService.findById(doctorId).orElse(null);

            if (patient == null || doctor == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(null);
            }

            Appointment appointment = new Appointment();
            appointment.setPatient(patient);
            appointment.setDoctor(doctor);
            appointment.setAppointmentTime(time);
            appointment.setNotes(body.get("notes"));

            Appointment saved = appointmentService.saveAppointment(appointment);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // ✅ Get All Appointments
    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    // ✅ Get Appointment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable("id") long id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        return appointment != null
                ? ResponseEntity.ok(appointment)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // ✅ Update Appointment
    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(
            @PathVariable("id") long id,
            @RequestBody Map<String, String> body) {

        Appointment existing = appointmentService.getAppointmentById(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Long patientId = Long.valueOf(body.get("patientId"));
        Long doctorId = Long.valueOf(body.get("doctorId"));
        LocalDateTime time = LocalDateTime.parse(body.get("appointmentTime"));

        Patient patient = patientService.findById(patientId).orElse(null);
        Doctor doctor = (Doctor) doctorService.findById(doctorId).orElse(null);

        if (patient == null || doctor == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        existing.setPatient(patient);
        existing.setDoctor(doctor);
        existing.setAppointmentTime(time);
        existing.setNotes(body.get("notes"));

        Appointment updated = appointmentService.saveAppointment(existing);
        return ResponseEntity.ok(updated);
    }

    // ✅ Delete Appointment
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable("id") long id) {
        boolean deleted = appointmentService.deleteAppointment(id);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Appointment not found");
        }
        return ResponseEntity.ok("Appointment deleted successfully.");
    }
}
