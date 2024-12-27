package com.trustt.Credit_Card_Service.Repository;

import com.trustt.Credit_Card_Service.Entity.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadRepository extends JpaRepository<Lead,Integer> {
}
