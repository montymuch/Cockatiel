package org.example.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.example.Mysql.service.impl.WebLogServiceImpl;
import org.example.record.WebLog;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.SQLException;

@Component
public class kafkaConsumer implements CommandLineRunner {
    public static void main(String[] args) throws SQLException {
        new kafkaConsumer().consumer();
    }
    public void consumer() throws SQLException {

        KafkaConsumer consumer = kafakFactoryBean.getKafkaConsumer("aop");
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000); // 轮询获取消息，超时时间为100毫秒
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("开始消费消息: key=" + record.key() + ", value=" + record.value());
                //数据库保存
             new WebLogServiceImpl().addWebLogDo(new WebLog( new Date(2024,4,21),"hahahahah","1232","asdasd",23123));
            }
        }
    }

    @Override
    public void run(String... args) throws Exception {
         consumer();
    }
}
