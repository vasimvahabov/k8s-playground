package com.example.k8s;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicReference;
import java.time.Instant;
import java.time.Duration;

@RestController
public class K8sAppController {

    private final AtomicReference<Instant> lastReadyTime;
    private final AvatarGenerator avatarGenerator;
    // private final PiCalculator piCalculator;

    public K8sAppController(
            AvatarGenerator avatarGenerator
            // PiCalculator piCalculator
    ) {
        this.lastReadyTime = new AtomicReference<>(Instant.now());
        this.avatarGenerator = avatarGenerator;
        // this.piCalculator = piCalculator;
    }

    @GetMapping("/client-ip")
    public ResponseEntity<String> clientIp(HttpServletRequest request) {
        var ipAddress = "Client IP: " + request.getRemoteAddr();
        return ResponseEntity.ok(ipAddress);
    }

    @GetMapping("/healtz")
    public ResponseEntity<Void> readinessProbe() {
        Instant now = Instant.now();
        Instant lastReady = lastReadyTime.get();

        if (now.isAfter(lastReady.plus(Duration.ofMinutes(5)))) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping("/readz")
    public ResponseEntity<Void> livenessProbe() {
        final boolean dependenciesConnected = Boolean.TRUE;

        if (dependenciesConnected) {
            lastReadyTime.set(Instant.now());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
    }

    // @GetMapping("/pi")
    // public ResponseEntity<BigDecimal> calculatePi(@RequestParam(defaultValue = "1000000") Integer iterations) {
    //     return ResponseEntity.ok(piCalculator.calculate(iterations));
    // }

    @GetMapping("/generate-avatar")
    public ResponseEntity<byte[]> generateAvatar() {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG_VALUE)
                .body(avatarGenerator.generate());
    }

}
