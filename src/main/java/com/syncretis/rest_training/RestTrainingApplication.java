package com.syncretis.rest_training;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestTrainingApplication {

    public static void main(String[] args) {
        //initialize zone
        SpringApplication application = new SpringApplication(RestTrainingApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
        //end of initialize zone
    }
}