package com.trustt.Credit_Card_Service.Repository;

import com.trustt.Credit_Card_Service.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

}
