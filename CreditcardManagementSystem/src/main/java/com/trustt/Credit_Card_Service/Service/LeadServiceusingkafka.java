package com.trustt.Credit_Card_Service.Service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class LeadServiceusingkafka {


    public class LeadProducer {

        private static final String TOPIC = "credit-card-application-leads";
        private static final String BOOTSTRAP_SERVERS = "localhost:9092";

        public void sendLead(String leadMessage) {
            // Kafka producer properties
            Properties properties = new Properties();
            properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
            properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
            properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

            // Create producer
            KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

            // Send message to Kafka topic
            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, "leadKey", leadMessage);
            producer.send(record, (metadata, exception) -> {
                if (exception != null) {
                    System.out.println("Error while producing: " + exception.getMessage());
                } else {
                    System.out.println("Sent message: " + leadMessage);
                }
            });

            // Close producer
            producer.close();
        }
    }
}

