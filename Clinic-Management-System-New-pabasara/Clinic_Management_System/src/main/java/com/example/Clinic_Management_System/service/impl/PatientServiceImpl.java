package com.example.Clinic_Management_System.service.impl;
import com.example.Clinic_Management_System.repository.PatientRepositary;
import com.example.Clinic_Management_System.service.PatientService;
import com.example.Clinic_Management_System.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


    @Service
    public  class PatientServiceImpl implements PatientService {

        @Autowired
        private PatientRepositary patientRepositary;

        @Override
        public Patient savePatient(Patient patient) {
            return patientRepositary.save(patient);
        }

        @Override
        public Patient getPatientById(long id) {
            return patientRepositary.findById(id)
                    .orElse(null);
        }

        @Override
        public Optional<Patient> findById(Long id) {
            return patientRepositary.findById(id);
        }

        @Override
        public Patient updatePatient(Patient patient, long id) {
            Patient existingPatient = patientRepositary.findById(id)
                    .orElse(null);

            if (existingPatient == null) {
                return null;
            }

            existingPatient.setFirstName(patient.getFirstName());
            existingPatient.setAge(patient.getAge());
            existingPatient.setGender(patient.getGender());
            existingPatient.setEmail(patient.getEmail());
            existingPatient.setPhone(patient.getPhone());
            existingPatient.setAddress(patient.getAddress());

            return patientRepositary.save(existingPatient);
        }

        @Override
        public boolean deletePatient(long id) {
            Optional<Patient> p = patientRepositary.findById(id);
            if (p.isPresent()) {
                patientRepositary.deleteById(id);
                return true;
            }
            return false;
        }

        @Override
        public List<Patient> getAllPatients() {
            return null;
        }
    }

