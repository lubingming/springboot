package com.lbm.provider1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Provider1Application {

    public static void main(String[] args) {
        SpringApplication.run(Provider1Application.class, args);
    }

}
