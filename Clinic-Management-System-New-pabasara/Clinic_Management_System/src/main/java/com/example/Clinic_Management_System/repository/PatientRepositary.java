package com.example.Clinic_Management_System.repository;
import com.example.Clinic_Management_System.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepositary extends JpaRepository<Patient, Long>{
}
