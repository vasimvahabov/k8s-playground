package com.example.k8s;

import java.util.Random;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AvatarGenerator {

    private final Environment environment;
    private final RestClient restClient;
    private final Random random;

    public AvatarGenerator(
            Environment environment,
            RestClient restClient
    ) {
        this.environment = environment;
        this.restClient = restClient;
        this.random = new Random();
    }

    public byte[] generate() {
        var avatarEndpoint = environment.getProperty("ROBOHASH_ENDPOINT");
        String url = avatarEndpoint + "/" + random.nextInt(101);
        return restClient.get()
                .uri(url)
                .retrieve()
                .body(byte[].class);
    }

}
