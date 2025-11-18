package com.ecoembes.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.ecoembes.application",   // paquete del Application
        "com.ecoembes.p1.controller", // tus controladores
        "com.AppServices",            // tu AppServices
        "com.dto",                    // tus DTOs
        "com.entity",                 // entidades
        "com.facade",                 // fachada
        "com.ServerState",            // estado del servidor
        "com"                         // escanea TODO lo que empiece por com.
})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}


