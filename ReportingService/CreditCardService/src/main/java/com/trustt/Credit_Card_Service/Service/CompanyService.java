package com.trustt.Credit_Card_Service.Service;

import com.trustt.Credit_Card_Service.DTO.CompanyDTO;
import com.trustt.Credit_Card_Service.Entity.Company;
import com.trustt.Credit_Card_Service.Repository.CompanyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Transactional
    public Company createCompany(CompanyDTO companyDTO) {
        Company company = new Company();
        company.setName(companyDTO.getName());
        company.setEmail(companyDTO.getEmail());
        company.setAge(companyDTO.getAge());
        company.setCreatedAt(LocalDateTime.now());
        return companyRepository.save(company);
    }
    public List<Company> getAllCompanies() {

        return companyRepository.findAll();
    }

    @Transactional
    public void deleteCompany(Integer id) {
        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Company with ID " + id + " does not exist.");
        }
    }
}