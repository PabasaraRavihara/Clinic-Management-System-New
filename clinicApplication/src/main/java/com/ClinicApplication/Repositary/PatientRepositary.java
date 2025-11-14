package com.ClinicApplication.Repositary;
import com.ClinicApplication.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepositary extends JpaRepository<Patient, Long>{
}
