package com.pulsefit.pulsefit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PulseFitApplication {

    public static void main(String[] args) {
        SpringApplication.run(PulseFitApplication.class, args);
    }

}
