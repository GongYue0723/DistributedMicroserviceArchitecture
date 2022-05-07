package com.example.distributedmicroservicearchitecture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, MongoAutoConfiguration.class})
public class DistributedMicroserviceArchitectureApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributedMicroserviceArchitectureApplication.class, args);
    }

}
