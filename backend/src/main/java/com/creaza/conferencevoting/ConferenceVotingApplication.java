package com.creaza.conferencevoting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.creaza.conferencevoting"})
@EnableSwagger2
public class ConferenceVotingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConferenceVotingApplication.class, args);
    }

}
