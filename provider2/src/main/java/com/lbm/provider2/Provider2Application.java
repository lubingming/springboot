package com.lbm.provider2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Provider2Application {

    public static void main(String[] args) {
        SpringApplication.run(Provider2Application.class, args);
    }

}
