package com.trustt.Credit_Card_Service.DTO;

import com.trustt.Credit_Card_Service.Entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private String customerName;
    private String email;
    private String phoneNo;
    private double accountBalance;
    private Integer creditScore;

}
