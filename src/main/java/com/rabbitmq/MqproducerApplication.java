package com.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:/package/spring-rabbitmq.xml")
public class MqproducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqproducerApplication.class, args);
    }

}
