package com.trustt.Credit_Card_Service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class LeadConsumer {

    @KafkaListener(topics = "credit-card-application-leads", groupId = "lead-consumer-group")
    public void consume(String leadMessage) {
        System.out.println("Received lead message: " + leadMessage);
    }
}
