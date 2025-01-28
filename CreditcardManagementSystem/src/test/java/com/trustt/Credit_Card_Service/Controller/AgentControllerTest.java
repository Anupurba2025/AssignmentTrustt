package com.trustt.Credit_Card_Service.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trustt.Credit_Card_Service.DTO.AgentDTO;
import com.trustt.Credit_Card_Service.Entity.Agent;
import com.trustt.Credit_Card_Service.Entity.Company;
import com.trustt.Credit_Card_Service.Repository.AgentRepository;
import com.trustt.Credit_Card_Service.Repository.CompanyRepository;
import com.trustt.Credit_Card_Service.Service.AgentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AgentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CompanyRepository companyRepository;// Mock CompanyRepository

    @Mock
    private AgentRepository agentRepository;
    @Mock
    private AgentService agentService;  // Mock AgentService

    @InjectMocks
    private AgentService actualAgentService;  // Use this for testing the service

    @InjectMocks
    private AgentController agentController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks

        // Ensure that the AgentService uses the mocked CompanyRepository
        actualAgentService = new AgentService();
        actualAgentService.setCompanyRepository(companyRepository); // Inject mock manually
    }
    @BeforeEach
    public void cleanUpDatabase() {
        companyRepository.deleteAll();
        agentRepository.deleteAll();
    }


    @Test
    public void testCreateAgent() throws Exception {
        // Create sample data for AgentDTO
        AgentDTO agentDTO = new AgentDTO();
        agentDTO.setName("Anupurba Kha");
        agentDTO.setEmail("anu2025@gmail.com");
        //agentDTO.setEmail("unique_email_" + System.currentTimeMillis() + "@gmail.com");
        agentDTO.setNo_of_success(0);
        agentDTO.setCompanyId(2);  // Set a valid companyId

        // Mock the CompanyRepository to return a company
        Company company = new Company();
        company.setCompanyId(2);
        company.setName("Test Company");
        Mockito.when(companyRepository.findById(1)).thenReturn(Optional.of(company));

        // Mock the AgentService to return a created agent
        Agent createdAgent = new Agent();
        createdAgent.setAgentId(1);
        createdAgent.setName(agentDTO.getName());
        createdAgent.setEmail(agentDTO.getEmail());
        createdAgent.setNoOfSuccessfulLeads(0);
        createdAgent.setCompany(company);
        Mockito.when(agentService.createAgent(Mockito.any())).thenReturn(createdAgent);

        // Perform the POST request
        mockMvc.perform(post("/agents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(agentDTO)))
                .andExpect(status().isCreated())
                //.andExpect(jsonPath("$.agentId").value(1))
                .andExpect(jsonPath("$.name").value("Anupurba Kha"))
                .andExpect(jsonPath("$.email").value("anu2025@gmail.com"));
    }
}

