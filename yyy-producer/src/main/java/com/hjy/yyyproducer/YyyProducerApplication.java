package com.hjy.yyyproducer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.hjy.yyyproducer.mapper")
public class YyyProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(YyyProducerApplication.class, args);
    }

}
