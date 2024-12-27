package com.trustt.Credit_Card_Service.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "agent")
@Getter
@Setter
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agent_id")
    private Integer agentId;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "email",nullable = false,unique = true)
    @Email
    private String email;

    @Column(name = "no_of_successful_leads")
    private Integer noOfSuccessfulLeads;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}
