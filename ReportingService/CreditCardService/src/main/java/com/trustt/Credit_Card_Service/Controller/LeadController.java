package com.trustt.Credit_Card_Service.Controller;

import com.trustt.Credit_Card_Service.DTO.LeadDTO;
import com.trustt.Credit_Card_Service.Entity.Lead;
import com.trustt.Credit_Card_Service.Service.LeadEvaluationService;
import com.trustt.Credit_Card_Service.Service.LeadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/leads")
public class LeadController {

    @Autowired
    private LeadService leadService;

    @Autowired
    private LeadEvaluationService leadEvaluationService;


    // Endpoint to create a new lead
    @PostMapping
    public Lead createLead(@RequestBody LeadDTO leadDTO) {
        return leadService.createLead(leadDTO);
    }

    // Endpoint to get all leads
    @GetMapping
    public List<Lead> getAllLeads() {
        return leadService.getAllLeads();
    }

    // Endpoint to delete a lead
    @DeleteMapping("/{leadId}")
    public void deleteLead(@PathVariable Integer leadId) {
        leadService.deleteLead(leadId);
    }

    // Endpoint to evaluate lead status
    @PutMapping("/{id}/evaluate")
    public ResponseEntity<Lead> evaluateLead(@PathVariable Integer id) {
        Lead lead = leadEvaluationService.evaluateLead(id);

        // Check if lead is not found
        if (lead == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // Lead not found
        }

        // Return updated lead
        return ResponseEntity.ok(lead);
    }
}

