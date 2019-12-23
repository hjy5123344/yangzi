package com.hjy.yyyeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class YyyEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(YyyEurekaServerApplication.class, args);
    }

}
