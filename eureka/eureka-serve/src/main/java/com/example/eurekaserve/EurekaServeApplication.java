package com.example.eurekaserve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //开启eureka注册中心的功能
public class EurekaServeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServeApplication.class, args);
    }

}
