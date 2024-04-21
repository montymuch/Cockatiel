package org.example.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class kafkaProducer {
    public static void main(String[] args) {
        new kafkaProducer().send("hhhhhhh");
    }

    public void send(Object obj){
        KafkaProducer kafkaProducer = kafakFactoryBean.getKafkaProducer();
        ProducerRecord<String, String> record = new ProducerRecord<>("aop", "key", obj.toString());

        kafkaProducer.send(record);
        kafkaProducer.close();
    }
}
