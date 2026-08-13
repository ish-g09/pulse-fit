package com.pulsefit.pulsefit;

import dev.langchain4j.openai.spring.AutoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = {AutoConfig.class})
@EnableAsync
public class PulseFitApplication {

    public static void main(String[] args) {
        SpringApplication.run(PulseFitApplication.class, args);
    }

}
