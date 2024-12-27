package com.trustt.Credit_Card_Service.Controller;
import com.trustt.Credit_Card_Service.kafka.LeadProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/kafka")
public class LeadControllerUsingKafka {



        @Autowired
        private LeadProducer leadProducer;

        @PostMapping("/publish")
        public String sendLead(@RequestBody String leadMessage) {
            leadProducer.sendLead(leadMessage);
            return "Lead message sent to Kafka!";
        }
    }


