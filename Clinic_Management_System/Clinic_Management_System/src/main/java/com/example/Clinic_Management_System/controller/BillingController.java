package com.example.Clinic_Management_System.controller;

import com.example.Clinic_Management_System.model.Billing;
import com.example.Clinic_Management_System.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/billings")
@CrossOrigin(origins = "*")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @GetMapping
    public ResponseEntity<List<Billing>> getAllBillings() {
        try {
            List<Billing> billings = billingService.getAllBillings();
            return new ResponseEntity<>(billings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Billing> getBillingById(@PathVariable("id") Long billId) {
        try {
            Optional<Billing> billing = billingService.getBillingById(billId);
            if (billing.isPresent()) {
                return new ResponseEntity<>(billing.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<List<Billing>> getBillingsByAppointmentId(@PathVariable("appointmentId") Long appointmentId) {
        try {
            List<Billing> billings = billingService.getBillingsByAppointmentId(appointmentId);
            return new ResponseEntity<>(billings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Billing>> getBillingsByStatus(@PathVariable("status") String status) {
        try {
            List<Billing> billings = billingService.getBillingsByStatus(status);
            return new ResponseEntity<>(billings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Billing> createBilling(@RequestBody Billing billing) {
        try {
            Billing createdBilling = billingService.createBilling(billing);
            return new ResponseEntity<>(createdBilling, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Billing> updateBilling(@PathVariable("id") Long billId, @RequestBody Billing billingDetails) {
        try {
            if (!billingService.billingExists(billId)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Billing updatedBilling = billingService.updateBilling(billId, billingDetails);
            return new ResponseEntity<>(updatedBilling, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBilling(@PathVariable("id") Long billId) {
        try {
            if (!billingService.billingExists(billId)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            billingService.deleteBilling(billId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/appointment/{appointmentId}/status/{status}")
    public ResponseEntity<Billing> getBillingByAppointmentAndStatus(
            @PathVariable("appointmentId") Long appointmentId,
            @PathVariable("status") String status) {
        try {
            Optional<Billing> billing = billingService.getBillingByAppointmentAndStatus(appointmentId, status);
            if (billing.isPresent()) {
                return new ResponseEntity<>(billing.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}