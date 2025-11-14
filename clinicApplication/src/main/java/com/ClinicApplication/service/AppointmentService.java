package com.ClinicApplication.service;
import com.ClinicApplication.model.Appointment;
import java.util.List;

public interface AppointmentService {


    Appointment createAppointment(Long doctorId, Appointment appointment);

    Appointment saveAppointment(Appointment appointment);

    // Get appointments for a doctor
    List<Appointment> getAppointmentsByDoctor(Long doctorId);

    List<Appointment> getAllAppointments();
    Appointment getAppointmentById(long id);
    Appointment updateAppointment(Appointment appointment, long id);
    boolean deleteAppointment(long id);

    List<Appointment> getAppointmentsByDoctorId(Long id);

    Appointment addAppointment(Long id, Appointment appointment);
}
