package com.ClinicApplication.service.Impl;

import com.ClinicApplication.Repositary.AppointmentRepositary;
import com.ClinicApplication.Repositary.DoctorRepositary;
import com.ClinicApplication.model.Appointment;
import com.ClinicApplication.model.Doctor;
import com.ClinicApplication.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepositary appointmentRepositary;

    @Autowired
    private DoctorRepositary doctorRepository;

    //  Add Appointment for a specific doctor
    @Override
    public Appointment addAppointment(Long doctorId, Appointment appointment) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with ID: " + doctorId));
        appointment.setDoctor(doctor);
        return appointmentRepositary.save(appointment);
    }

    //  Get appointments for a specific doctor
    @Override
    public List<Appointment> getAppointmentsByDoctorId(Long doctorId) {
        return appointmentRepositary.findByDoctorId(doctorId);
    }

    //  Create Appointment (general)
    @Override
    public Appointment createAppointment(Long doctorId, Appointment appointment) {
        return addAppointment(doctorId, appointment);
    }

    //  Save appointment in the database
    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepositary.save(appointment);
    }

    @Override
    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
        return null;
    }

    //  Get all appointments
    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepositary.findAll();
    }

    //  Get appointment by ID
    @Override
    public Appointment getAppointmentById(long id) {
        return appointmentRepositary.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + id));
    }

   // Update appointment
    @Override
    public Appointment updateAppointment(Appointment appointment, long id) {
        Appointment existingAppointment = appointmentRepositary.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + id));

        existingAppointment.setDate(appointment.getDate());
        existingAppointment.setTime(appointment.getTime());
        existingAppointment.setDoctor(appointment.getDoctor());
        existingAppointment.setPatient(appointment.getPatient());
        existingAppointment.setStatus(appointment.getStatus());

        return appointmentRepositary.save(existingAppointment);
    }


    @Override
    public boolean deleteAppointment(long id) {
        if (!appointmentRepositary.existsById(id)) {
            return false;
        }
        appointmentRepositary.deleteById(id);
        return true;
    }
}
