package com.trustt.Credit_Card_Service.DTO;

import com.trustt.Credit_Card_Service.Entity.Agent;
import com.trustt.Credit_Card_Service.Entity.Customer;
import com.trustt.Credit_Card_Service.Entity.LeadStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeadDTO {
    private String referenceNumber;
    private Integer customerId;
    private Integer agentId;
    private LeadStatus status;
    private LocalDateTime createdAt;

}
