package com.hjy.yyyconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class YyyConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(YyyConsumerApplication.class, args);
    }

}
