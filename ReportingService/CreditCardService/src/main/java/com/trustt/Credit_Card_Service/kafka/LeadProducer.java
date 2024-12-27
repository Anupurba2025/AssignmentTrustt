package com.trustt.Credit_Card_Service.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import java.util.Properties;
@Component
public class LeadProducer {

    private KafkaProducer<String, String> producer;

    public LeadProducer() {
        // Kafka producer properties
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Creating a new producer
        this.producer = new KafkaProducer<>(properties);
    }

    // Method to send the lead message
    public void sendLead(String leadMessage) {
        // Send message to topic 'credit-card-application-leads'
        ProducerRecord<String, String> record = new ProducerRecord<>("credit-card-application-leads", "leadKey", leadMessage);
        producer.send(record);
    }

    public void close() {
        producer.close();
    }

    public static void main(String[] args) {
        LeadProducer leadProducer = new LeadProducer();
        // Create message (Lead Data)
        String leadMessage = "{ \"leadId\": 1, \"customerId\": 101, \"agentId\": 2001, \"status\": \"PENDING\" }";

        // Send the lead message using sendLead()
        leadProducer.sendLead(leadMessage);

        leadProducer.close();
    }
}
