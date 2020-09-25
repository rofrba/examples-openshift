package com.redhat.delayinit;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    Logger logger = LoggerFactory.getLogger(TestController.class);
    int i = 1;

    @GetMapping(path = "/")
    public ResponseEntity<Object> sayHello() {

        logger.info("Request number: " + i);
        i++;
        try {
            TimeUnit.MILLISECONDS.sleep(30);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body("Service UP");
    }
}