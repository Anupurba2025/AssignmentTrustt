package com.trustt.Credit_Card_Service.Service;

import com.trustt.Credit_Card_Service.DTO.AgentDTO;
import com.trustt.Credit_Card_Service.Entity.Agent;
import com.trustt.Credit_Card_Service.Entity.Company;
import com.trustt.Credit_Card_Service.Repository.AgentRepository;
import com.trustt.Credit_Card_Service.Repository.CompanyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Transactional
    public Agent createAgent(AgentDTO agentDTO) {
        Company company = companyRepository.findById(agentDTO.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        Agent agent = new Agent();
        agent.setName(agentDTO.getName());
        agent.setEmail(agentDTO.getEmail());
        agent.setNoOfSuccessfulLeads(0);
        agent.setCompany(company);

        // Save and return the Agent entity
        return agentRepository.save(agent);
    }
    public List<Agent> getAllAgents() {
        return agentRepository.findAll();  // Fetching all agents from the database
    }

    public void setCompanyRepository(CompanyRepository companyRepository) {
    }
}