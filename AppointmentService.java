package com.example.Clinic_Management_System.service;
import com.example.Clinic_Management_System.model.Appointment;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    static List<Appointment> getAppointmentByDoctor(Long id) {
        return null;
    }

    static Appointment addSchedule(Long id, Appointment schedule) {
        return null;
    }



    Appointment createAppointment(Long doctorId, Appointment appointment);

    Appointment saveAppointment(Appointment appointment);

    // Get appointments for a doctor
    List<Appointment> getAppointmentsByDoctor(Long doctorId);

    List<Appointment> getAllAppointments();
    Appointment getAppointmentById(long id);
    Appointment updateAppointment(Appointment appointment, long id);
    void deleteAppointment(long id);

    Appointment save(Appointment appointment);

    Appointment findById(long id);

    void delete(long id);
}
