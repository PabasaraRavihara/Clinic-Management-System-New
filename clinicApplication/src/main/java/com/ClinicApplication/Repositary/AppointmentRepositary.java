package com.ClinicApplication.Repositary;
import com.ClinicApplication.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepositary extends JpaRepository<Appointment, Long>{
    List<Appointment> findAllById(Long doctorId);

    List<Appointment> findByDoctorId(Long doctorId);
}
