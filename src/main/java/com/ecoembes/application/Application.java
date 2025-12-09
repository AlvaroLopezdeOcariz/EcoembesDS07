package com.ecoembes.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.ecoembes.application",   
        "com.ecoembes.p1.controller", 
        "com.AppServices",           
                           
})

@EntityScan(basePackages = "com.entity")
@EnableJpaRepositories(basePackages = "dao")

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}


