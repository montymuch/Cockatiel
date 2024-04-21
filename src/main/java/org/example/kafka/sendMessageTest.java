package org.example.kafka;

import java.sql.SQLException;

public class sendMessageTest {
    public static void main(String[] args) throws SQLException {
       new kafkaConsumer().consumer();
    }
}
