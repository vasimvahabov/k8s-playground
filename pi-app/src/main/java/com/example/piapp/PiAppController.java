package com.example.piapp;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class PiAppController {

    private final PiAppService piAppService;

    public PiAppController(
        PiAppService piAppService
    ){
        this.piAppService = piAppService;
    }

    @GetMapping("/pi/{iterations}")
    public ResponseEntity<Void> calculatePi(@PathVariable Long iterations) {
        piAppService.addTaskToQueue(iterations);
        return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.NO_CONTENT.value()));
    }

}
