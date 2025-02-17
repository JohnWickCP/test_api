package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo4Application {
    private static final Logger logger = LoggerFactory.getLogger(Demo4Application.class);

    public static void main(String[] args) {
        logger.info("Starting Demo4Application...");
        SpringApplication.run(Demo4Application.class, args);
        logger.info("Demo4Application started successfully.");
    }
}