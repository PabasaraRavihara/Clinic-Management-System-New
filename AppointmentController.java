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

    // ✅ Create Appointment (POST)
    @PostMapping
    public ResponseEntity<Appointment> saveAppointment(@RequestBody Map<String, String> body) {
        Long patientId = Long.valueOf(body.get("patientId"));
        Long doctorId = Long.valueOf(body.get("doctorId"));
        LocalDateTime time = LocalDateTime.parse(body.get("appointmentTime"));

        Patient patient = patientService.findById(patientId);
        Doctor doctor = doctorService.findById(doctorId);

        if (patient == null || doctor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentTime(time);
        appointment.setNotes(body.get("notes"));

        return new ResponseEntity<>(appointmentService.save(appointment), HttpStatus.CREATED);
    }

    // ✅ Get All Appointments
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    // ✅ Get Appointment by ID
    @GetMapping("{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable("id") long id) {
        Appointment appointment = appointmentService.findById(id);
        if (appointment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    // ✅ Update Appointment
    @PutMapping("{id}")
    public ResponseEntity<Appointment> updateAppointment(
            @PathVariable("id") long id,
            @RequestBody Map<String, String> body) {

        Appointment existing = appointmentService.findById(id);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Long patientId = Long.valueOf(body.get("patientId"));
        Long doctorId = Long.valueOf(body.get("doctorId"));
        LocalDateTime time = LocalDateTime.parse(body.get("appointmentTime"));

        Patient patient = patientService.findById(patientId);
        Doctor doctor = doctorService.findById(doctorId);

        if (patient == null || doctor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        existing.setPatient(patient);
        existing.setDoctor(doctor);
        existing.setAppointmentTime(time);
        existing.setNotes(body.get("notes"));

        return new ResponseEntity<>(appointmentService.save(existing), HttpStatus.OK);
    }

    // ✅ Delete Appointment
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable("id") long id) {
        Appointment appointment = appointmentService.findById(id);
        if (appointment == null) {
            return new ResponseEntity<>("Appointment not found", HttpStatus.NOT_FOUND);
        }
        appointmentService.delete(id);
        return new ResponseEntity<>("Appointment deleted successfully.", HttpStatus.OK);
    }
}

