package com.loganaxel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.loganaxel.Repository")
public class Main {
    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }
}