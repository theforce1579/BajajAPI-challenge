package com.bajajchallenge.bhfl_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.bajajchallenge.bhfl_api")
public class BhflApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BhflApiApplication.class, args);
    }
}