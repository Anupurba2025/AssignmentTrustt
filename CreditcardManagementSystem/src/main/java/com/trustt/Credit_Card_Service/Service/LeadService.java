package com.trustt.Credit_Card_Service.Service;

import com.trustt.Credit_Card_Service.DTO.LeadDTO;
import com.trustt.Credit_Card_Service.Entity.Agent;
import com.trustt.Credit_Card_Service.Entity.Customer;
import com.trustt.Credit_Card_Service.Entity.Lead;
import com.trustt.Credit_Card_Service.Entity.LeadStatus;
import com.trustt.Credit_Card_Service.Repository.LeadRepository;
import com.trustt.Credit_Card_Service.Repository.AgentRepository;
import com.trustt.Credit_Card_Service.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class LeadService {

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AgentRepository agentRepository;


    @Transactional
    public Lead createLead(LeadDTO leadDTO) {
        // Validate customer and agent existence
        Customer customer = customerRepository.findById(leadDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with ID " + leadDTO.getCustomerId()));

        Agent agent = agentRepository.findById(leadDTO.getAgentId())
                .orElseThrow(() -> new RuntimeException("Agent not found with ID " + leadDTO.getAgentId()));

        // Generate a unique reference number for the lead
        String referenceNumber = UUID.randomUUID().toString();

        // Create a new Lead entity
        Lead lead = new Lead();
        lead.setReferenceNumber(referenceNumber);
        lead.setCustomer(customer);
        lead.setAgent(agent);
        lead.setStatus(LeadStatus.PENDING);
        lead.setCreatedAt(LocalDateTime.now());

        // Save the lead to the repository
        return leadRepository.save(lead);
    }

    // Get All Leads Method
    public List<Lead> getAllLeads() {
        return leadRepository.findAll();
    }

    // Delete Lead Method
    @Transactional
    public void deleteLead(Integer leadId) {
        if (leadRepository.existsById(leadId)) {
            leadRepository.deleteById(leadId);
        } else {
            throw new RuntimeException("Lead with ID " + leadId + " does not exist.");
        }
    }
}
