package com.example.piapp;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class PiAppService {

    private final JedisPool jedisPool;

    public PiAppService(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public void addTaskToQueue(Long iterations){
        System.out.println("Adding task to queue ...");
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.rpush("queue:task", iterations.toString());
        } catch(Exception exception) {
            System.out.println("Exception occured while adding task to queue : " + exception.getMessage());
        }
        System.out.println("Added task to queue ...");
    }

}
