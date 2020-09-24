package com.redhat.delay;

import java.util.concurrent.TimeUnit;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DelayApplication {

	public static void main(String[] args) {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SpringApplication.run(DelayApplication.class, args);
	}

}
