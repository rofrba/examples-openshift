package com.redhat.helloworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    Logger logger = LoggerFactory.getLogger(TestController.class);
    int i=1;
    @GetMapping(path = "/")
    public ResponseEntity<Object> sayHello()  {

        logger.info("Request number: "+i);

        insert_data("http://www.bussalud.gov.ar", "2021-06-28 15:04:38.561285", null, "http://msgc.gcba.gob.ar", null, null, "masterfile-federacion-service/fhir/Patient");
        insert_data("hospitalitaliano.org.ar", "2021-06-28 15:04:39.127933", null, "sec.salud.muni.cordoba", "51", null, "/masterfile-federacion-service/api/personas/renaper");
        insert_data("hospitalitaliano.org.ar", "2021-06-28 15:04:40.149939", null, "secretaria.tecnologias.gobierno.santafe", "85", null, "/masterfile-federacion-service/api/personas/renaper");
        insert_data("hospitalitaliano.org.ar", "2021-06-28 15:04:40.648254", null, "ministerio.neuquen", "7", null, "/masterfile-federacion-service/api/personas/cobertura");
        insert_data("hospitalitaliano.org.ar", "2021-06-28 15:04:42.560667", null, "ministerio.neuquen", "7", null, "/masterfile-federacion-service/api/personas/renaper");
        insert_data("http://www.bussalud.gov.ar", "2021-06-28 15:04:42.567586", null, "https://app.andes.gob.ar", null, null, "masterfile-federacion-service/fhir/Patient");
        insert_data("hospitalitaliano.org.ar", "2021-06-28 15:04:43.025366", null, "sec.salud.muni.cordoba", "51", null, "/masterfile-federacion-service/api/personas/renaper");
        insert_data("hospitalitaliano.org.ar", "2021-06-28 15:04:44.520133", null, "ministerio.salud.sanjuan", "46", null, "/masterfile-federacion-service/api/personas/cobertura");
        insert_data("http://www.bussalud.gov.ar", "2021-06-28 15:04:45.640256", null, "https://app.andes.gob.ar", null, null, "masterfile-federacion-service/fhir/Patient");
        insert_data("hospitalitaliano.org.ar", "2021-06-28 15:04:48.026646", null, "ministerio.neuquen", "7", null, "/masterfile-federacion-service/api/personas/renaper");
        insert_data("hospitalitaliano.org.ar", "2021-06-28 15:04:48.535775", null, "masterfile", "2", null, "/masterfile-federacion-service/api/personas/renaper/");
        i++;

        return ResponseEntity.status(HttpStatus.OK).body("Service UP v3!");
    }

    public void insert_data(String iss, String created_at, String des, String sub, String user_id, String type, String path){
        //iss       http://www.bussalud.gov.ar
        //fecha     2021-06-28 15:04:38.561285
        //sub       http://msgc.gcba.gob.ar
        //user_id   null
        //path      masterfile-federacion-service/fhir/Patient
        //{"iss": "hospitalitaliano.org.ar","created_at": "2021-06-28 15:04:48.026646","sub": "ministerio.neuquen","user_id": "7","path": "/masterfile-federacion-service/api/personas/renaper"}
        logger.info("{\"iss\": \"" + iss + "\",\"created_at\": \"" + created_at + "\",\"sub\": \"" + sub + "\",\"user_id\": \"" + user_id + "\",\"path\": \"" + path + "\"}");
    }
}
