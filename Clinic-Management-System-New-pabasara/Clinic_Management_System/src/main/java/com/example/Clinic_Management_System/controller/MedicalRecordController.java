package com.example.Clinic_Management_System.controller;
import com.example.Clinic_Management_System.model.MedicalRecord;
import com.example.Clinic_Management_System.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medical-records")
@CrossOrigin(origins = "*")
public class MedicalRecordController {
    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping
    public List<MedicalRecord> getAllRecords() {
        return medicalRecordService.getAllRecords();
    }

    @GetMapping("/{id}")
    public Optional<MedicalRecord> getRecordById(@PathVariable Long id) {
        return medicalRecordService.getRecordById(id);
    }

    @PostMapping
    public MedicalRecord createRecord(@RequestBody MedicalRecord record) {
        return medicalRecordService.createRecord(record);
    }

    @PutMapping("/{id}")
    public MedicalRecord updateRecord(@PathVariable Long id, @RequestBody MedicalRecord record) {
        return medicalRecordService.updateRecord(id, record);
    }

    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable Long id) {
        medicalRecordService.deleteRecord(id);
    }
}
