package com.trustt.Credit_Card_Service.Repository;

import com.trustt.Credit_Card_Service.Entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Integer> {

}
