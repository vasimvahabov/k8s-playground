// package com.example.k8s;

// import java.math.BigDecimal;
// import org.springframework.core.env.Environment;
// import org.springframework.web.client.RestClient;

// public class PiCalculator {

//     private final Environment environment;
//     private final RestClient restClient;

//     public PiCalculator(
//             Environment environment,
//             RestClient restClient) {
//         this.environment = environment;
//         this.restClient = restClient;
//     }

//     public BigDecimal calculate(Integer iterations) {
//         var piAppEndpoint = environment.getProperty("PI_APP_ENDPOINT");
//         String url = piAppEndpoint + "/" + iterations;
//         return restClient.get()
//                 .uri(url)
//                 .retrieve()
//                 .body(BigDecimal.class);
//     }
// }
