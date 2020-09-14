package com.redhat.servicea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;


@Controller
public class ServiceConnectController {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Value("${SERVICE_B_URL}")
    private String URL;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(path = "/")
    public ResponseEntity<Object> sayHello() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Hello");
    }

    @GetMapping(path = "/connect")
    public ResponseEntity<Object> connectTo()  {  
        String result = "";
        
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);
            result ="Connection with service B OK ";
            if (response.getStatusCode() == HttpStatus.OK) return ResponseEntity.status(HttpStatus.OK).body(result);

        } catch(HttpClientErrorException ex){ //Cuando se recibe algún error 4xx
            result=ex.getResponseBodyAsString();
            return ResponseEntity.badRequest().body(result);
            
        }catch(HttpServerErrorException ex){ //Cuando se recibe algún error 5xx
            result=ex.getResponseBodyAsString();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        
        }catch(Exception ex){
            result="Error en comunnicación";
        }
  
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }

    
}

