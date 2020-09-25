package com.redhat.delayinit;

import java.util.concurrent.TimeUnit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DelayInitApplication {

	public static void main(String[] args) {
		try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

		SpringApplication.run(DelayInitApplication.class, args);
	}

}
