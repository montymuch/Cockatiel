package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

public class redistest {

    private Jedis jedis;

    @BeforeEach
    void setup(){
       jedis = new Jedis("localhost",6379);

    }
    @Test
    public  void redisStringTest(){
        String a = jedis.get("a");
        System.out.println(a);
    }

    @Test
    public void redisHashTest(){



    }
}
