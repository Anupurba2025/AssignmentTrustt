package com.trustt.Credit_Card_Service.Service;

import com.trustt.Credit_Card_Service.Entity.Agent;
import com.trustt.Credit_Card_Service.Entity.Customer;
import com.trustt.Credit_Card_Service.Entity.Lead;
import com.trustt.Credit_Card_Service.Entity.LeadStatus;
import com.trustt.Credit_Card_Service.Repository.AgentRepository;
import com.trustt.Credit_Card_Service.Repository.LeadRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeadEvaluationService {

    @Autowired
    LeadRepository leadRepository;

    @Autowired
    AgentRepository agentRepository;
    public Lead evaluateLead(Integer leadId) {


        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new EntityNotFoundException("Lead not found with ID " + leadId));

        Customer customer = lead.getCustomer();

        // Evaluate lead based on customer civil score and account balance
        if (customer.getCreditScore() >= 700 && customer.getAccountBalance() >= 50000) {
            lead.setStatus(LeadStatus.APPROVED);

            // Increment the successful leads count for the associated agent
            Agent agent = lead.getAgent();
            if (agent != null) {
                agent.setNoOfSuccessfulLeads(agent.getNoOfSuccessfulLeads() + 1);
                agentRepository.save(agent);
            }
        }
        // Insufficient balance, but civil score is okay
        // On hold if balance < 50k
        else if (customer.getAccountBalance() < 50000) {
            lead.setStatus(LeadStatus.HOLD);
        }
        // Either insufficient civil score or other conditions
        // Rejected if any of the conditions fail
        else {
            lead.setStatus(LeadStatus.REJECTED);
        }

        // Save the updated lead with its new status
        return leadRepository.save(lead);
    }
}

/*


@Service
public class LeadEvaluationService {

    @Autowired
    LeadRepository leadRepository;

    @Autowired
    LeadProducerService leadProducerService; // Injecting Kafka producer

    public Lead evaluateLead(Integer leadId) {
        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new EntityNotFoundException("Lead not found with ID " + leadId));

        // Evaluate lead based on customer data (credit score and account balance)
        if (lead.getCustomer().getCreditScore() >= 700 && lead.getCustomer().getAccountBalance() >= 50000) {
            lead.setStatus(LeadStatus.APPROVED);
            // Send the event to Kafka when the lead is approved
            leadProducerService.sendLeadApprovalEvent(leadId.toString(), lead.getAgent().getAgentId().toString());
        } else if (lead.getCustomer().getAccountBalance() < 50000) {
            lead.setStatus(LeadStatus.HOLD);
        } else {
            lead.setStatus(LeadStatus.REJECTED);
        }

        return leadRepository.save(lead);
    }
}
*/