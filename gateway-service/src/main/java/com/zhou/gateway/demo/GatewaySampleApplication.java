package com.zhou.gateway.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.atomic.LongAdder;


@SpringBootApplication
public class GatewaySampleApplication {

    
    public static void main(String[] args) {
        //LongAdder
        SpringApplication.run(GatewaySampleApplication.class, args);
        }
}