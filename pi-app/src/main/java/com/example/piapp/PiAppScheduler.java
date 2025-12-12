package com.example.piapp;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PiAppScheduler {

    // private final Logger logger =
    // LoggerFactory.getLogger(PiAppApplication.class);

    @Scheduled(fixedDelay = 20000, initialDelay = 10000)
    public void writeTimeStamp() {
        Path path = Paths.get("timestamp.current");
        Instant now = Instant.now();

    try (BufferedWriter writer = Files.newBufferedWriter(
            path,
            StandardCharsets.UTF_8,
            StandardOpenOption.CREATE,
            StandardOpenOption.WRITE
    )) {
        writer.write(String.valueOf(now.getEpochSecond()));
        writer.newLine();
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
