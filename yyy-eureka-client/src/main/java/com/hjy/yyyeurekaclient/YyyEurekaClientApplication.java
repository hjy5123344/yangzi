package com.hjy.yyyeurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class YyyEurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(YyyEurekaClientApplication.class, args);
    }

}
