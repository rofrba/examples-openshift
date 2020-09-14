package com.redhat.delay;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DelayController {

    @Value("${DELAY}")
    private Long seconds;

    @GetMapping(path = "/")
    public ResponseEntity<Object> sayHello() {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Service UP!");
    }
    
}
