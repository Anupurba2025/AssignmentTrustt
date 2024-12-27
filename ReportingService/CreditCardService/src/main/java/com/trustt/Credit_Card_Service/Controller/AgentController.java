package com.trustt.Credit_Card_Service.Controller;

import com.trustt.Credit_Card_Service.DTO.AgentDTO;
import com.trustt.Credit_Card_Service.Entity.Agent;
import com.trustt.Credit_Card_Service.Entity.Company;
import com.trustt.Credit_Card_Service.Service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agents")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @PostMapping
    public ResponseEntity<Agent> createAgent(@RequestBody AgentDTO agentDTO){
        Agent createdAgent = agentService.createAgent(agentDTO);
        return new ResponseEntity<>(createdAgent, HttpStatus.CREATED);
    }
    @GetMapping("/details")
    public ResponseEntity<List<Agent>> getAgent(){
        List<Agent> agents = agentService.getAllAgents();
        return new ResponseEntity<>(agents, HttpStatus.OK);
    }
}
