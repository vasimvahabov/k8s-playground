package com.example.piapp;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import jakarta.annotation.PreDestroy;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class PiTaskConsumer {

    private final JedisPool jedisPool;
    private boolean running = Boolean.TRUE;

    private final ConfigurableApplicationContext context;

    public PiTaskConsumer(
            ConfigurableApplicationContext context,
            JedisPool jedisPool
    ) {
        this.jedisPool = jedisPool;
        this.context = context;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void consumeTasks() {
        System.out.println("Starting consumer...");
        try (Jedis jedis = jedisPool.getResource()) {
            while (running) {
                List<String> task = jedis.blpop(60, "queue:task");
                if (task != null && task.size() == 2) {
                    String value = task.get(1);
                    System.out.println("Got value: " + value);
                    long iterations = Long.parseLong(value);
                    BigDecimal pi = calculatePi(iterations);
                    System.out.println("Computed π: " + pi);
                } else {
                    System.out.println("No task available in queue after 60 seconds.");
                    break;
                }
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        } finally {
            System.out.println("Consumer shutting down ...");
            System.exit(SpringApplication.exit(context, () -> 0));
        }
    }

    private BigDecimal calculatePi(long iterations) {
        MathContext mc = new MathContext(20, RoundingMode.HALF_UP);
        BigDecimal piDiv4 = BigDecimal.ONE;
        BigDecimal odd = new BigDecimal(3);

        for (int i = 0; i < iterations; i++) {
            piDiv4 = piDiv4.subtract(BigDecimal.ONE.divide(odd, mc));
            odd = odd.add(BigDecimal.valueOf(2));
            piDiv4 = piDiv4.add(BigDecimal.ONE.divide(odd, mc));
            odd = odd.add(BigDecimal.valueOf(2));
        }

        return piDiv4.multiply(BigDecimal.valueOf(4), mc);
    }

    @PreDestroy
    private void shutDown() {
        System.out.println("Sigterm request received ...");
        running = Boolean.FALSE;
    }

}
