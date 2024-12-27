package com.trustt.Credit_Card_Service.Repository;

import com.trustt.Credit_Card_Service.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    // Example: Find a company by email
    Optional<Company> findByEmail(String email);
}
