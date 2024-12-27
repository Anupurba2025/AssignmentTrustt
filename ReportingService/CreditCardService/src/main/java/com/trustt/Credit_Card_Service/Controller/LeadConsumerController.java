package com.trustt.Credit_Card_Service.Controller;
import com.trustt.Credit_Card_Service.Service.LeadConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kafka")
public class LeadConsumerController {

    @Autowired
    private LeadConsumerService leadConsumerService;

    @PostMapping("/consume")
    public String consumeLeadMessages() {
        leadConsumerService.consumeLeadMessages();
        return "Lead messages consumed from Kafka!";
    }
}
