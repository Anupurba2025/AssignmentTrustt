package com.trustt.Credit_Card_Service.Service;

import com.trustt.Credit_Card_Service.DTO.CustomerDTO;
import com.trustt.Credit_Card_Service.Entity.Customer;
import com.trustt.Credit_Card_Service.Repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Customer createCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setName(customerDTO.getCustomerName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNo(customerDTO.getPhoneNo());
        customer.setAccountBalance(customerDTO.getAccountBalance());
        customer.setCreditScore(customerDTO.getCreditScore());
        return customerRepository.save(customer);
    }
    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }
    @Transactional
    public void deleteCustomer(Integer id) {
        if(customerRepository.existsById(id)){
            customerRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Customer with ID " + id + " does not exist.");
        }
    }
}
