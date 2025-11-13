package com.example.Clinic_Management_System.service.impl;
import com.example.Clinic_Management_System.repository.DoctorRepositary;
import com.example.Clinic_Management_System.model.Appointment;
import com.example.Clinic_Management_System.repository.AppointmentRepositary;
import com.example.Clinic_Management_System.model.Doctor;
import com.example.Clinic_Management_System.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    @Autowired
    private AppointmentRepositary appointmentRepositary;
    @Autowired
    private DoctorRepositary doctorRepository;



    // Create Appointment

    @Override
    public Appointment createAppointment(Long doctorId, Appointment appointment) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        appointment.setDoctor(doctor);
        return appointmentRepositary.save(appointment);
    }


    // Save appointment in the database
    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepositary.save(appointment);
    }

    // Get appointments for a doctor
    @Override
    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
        return appointmentRepositary.findAllById(doctorId);
    }

    // Get all appointments from the database
    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepositary.findAll();
    }

    // Get appointment by ID
    @Override
    public Appointment getAppointmentById(long id) {
        return appointmentRepositary.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
    }

    // Update appointment
    @Override
    public Appointment updateAppointment(Appointment appointment, long id) {
        Appointment existingAppointment = appointmentRepositary.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));

        existingAppointment.setDate(appointment.getDate());
        existingAppointment.setTime(appointment.getTime());
        existingAppointment.setDoctor(appointment.getDoctor());
        existingAppointment.setPatient(appointment.getPatient());
        existingAppointment.setStatus(appointment.getStatus());

        appointmentRepositary.save(existingAppointment);
        return existingAppointment;
    }

    // Delete appointment
    @Override
    public void deleteAppointment(long id) {
        appointmentRepositary.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));

        appointmentRepositary.deleteById(id);
    }

    @Override
    public Appointment save(Appointment appointment) {
        return null;
    }

    @Override
    public Appointment findById(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
