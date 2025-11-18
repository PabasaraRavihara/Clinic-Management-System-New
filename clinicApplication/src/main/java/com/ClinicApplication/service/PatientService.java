package com.ClinicApplication.service;
import com.ClinicApplication.model.Patient;
import com.ClinicApplication.Repositary.PatientRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface PatientService {

    Patient savePatient(Patient patient);

    Patient getPatientById(long id);

    Optional<Patient> findById(Long id);

    Patient updatePatient(Patient patient, long id);

    boolean deletePatient(long id);

    List<Patient> getAllPatients();
}
