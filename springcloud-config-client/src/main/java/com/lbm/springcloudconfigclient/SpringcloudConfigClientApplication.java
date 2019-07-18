package com.lbm.springcloudconfigclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringcloudConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudConfigClientApplication.class, args);
    }

}
