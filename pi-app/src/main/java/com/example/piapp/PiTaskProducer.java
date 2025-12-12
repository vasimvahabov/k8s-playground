package com.example.piapp;

import java.util.Random;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PiTaskProducer implements CommandLineRunner {

    private final PiAppService piAppService;
    private final Random random;

    public PiTaskProducer(PiAppService piAppService) {
        this.piAppService = piAppService;
        this.random = new Random();
    }

    @Override
    public void run(String... args) throws Exception {
        // int taskCount = random.nextInt(10000, 15000);
        int taskCount = 100;
        for (int i = 0; i < 100; i++) {
            long iterations = random.nextLong(1000000, 5000000);
            piAppService.addTaskToQueue(iterations);
        }
    }

}
