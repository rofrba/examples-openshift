package com.redhat.helloworld;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping(path = "/")
    public ResponseEntity<Object> sayHello()  {  
         
        return ResponseEntity.status(HttpStatus.OK).body("Service UP!");
    }
}