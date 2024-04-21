package org.example.cache;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class RedisFactroy {

    private static Jedis jedis = new Jedis("localhost",6379);
    private RedisFactroy(){}
    public static Jedis getInstance(){
        return  jedis;
    }

}
