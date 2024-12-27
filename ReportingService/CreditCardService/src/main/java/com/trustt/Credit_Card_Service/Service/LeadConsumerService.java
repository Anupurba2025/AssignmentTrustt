package com.trustt.Credit_Card_Service.Service;


import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Properties;

@Service
public class LeadConsumerService {

    public void consumeLeadMessages() {

        // Kafka consumer properties
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("group.id", "lead-consumer-group");
        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", StringDeserializer.class.getName());
        properties.put("auto.offset.reset", "earliest");

        // Create consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        // Subscribe to the topic
        consumer.subscribe(Collections.singletonList("credit-card-application-leads"));

        // Poll for messages
        consumer.poll(100).forEach(record -> {
            System.out.println("Received lead message: " + record.value());
        });

        consumer.close();
    }
}
