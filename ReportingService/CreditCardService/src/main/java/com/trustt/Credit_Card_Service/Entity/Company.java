package com.trustt.Credit_Card_Service.Entity;

import jakarta.persistence.*;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "company")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "email", nullable = false,unique = true)
    @Email(message = "Invalid email format")
    private String email;

    @Column(name = "age")
    private Integer age;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}
