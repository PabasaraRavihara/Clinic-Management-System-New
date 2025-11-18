//package com.ClinicApplication.init;
//import com.ClinicApplication.model.Doctor;
//import com.ClinicApplication.model.Patient;
//import com.ClinicApplication.Repositary.DoctorRepositary;
//import com.ClinicApplication.Repositary.PatientRepositary;
//import jakarta.annotation.PostConstruct;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataInitializer {
//    private final PatientRepositary patientRepo;
//    private final DoctorRepositary doctorRepo;
//
//    public DataInitializer(PatientRepositary patientRepo, DoctorRepositary doctorRepo) {
//        this.patientRepo = patientRepo;
//        this.doctorRepo = doctorRepo;
//    }
//
//    @PostConstruct
//    public void init() {
//        Patient p1 = new Patient();
//        p1.setFirstName("Saman"); p1.setLastName("Perera"); p1.setEmail("saman@example.com");
//        patientRepo.save(p1);
//
//        Doctor d1 = new Doctor();
//        d1.setName("Dr. Nimal"); d1.setSpecialization("General");
//        doctorRepo.save(d1);
//    }
//}
