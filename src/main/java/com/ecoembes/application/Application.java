package com.ecoembes.application;

/* Para acceder a PlasSB mediante Ecoembes: http://localhost:8080/plantas/capacidad?idPlanta=1&fecha=2025-01-16&token=TOKEN_FIJO
 * Para acceder a Swagger UI: http://localhost:8080/swagger-ui/index.html
 * Para acceder a H2 Console: http://localhost:8080/h2-console
 * Para acceder a PlasSB directamente: http://localhost:8081/plassb/capacidad?fecha=2025-01-15
 * Para acceder a ContSocket directamente: http://localhost:8083/contsocket/capacidad?fecha=2025-01-17
 * Las fechas en los endpoints deben estar en formato AAAA-MM-DD, y se puede usar cualquier fecha válida, 
 * que esté en la base de datos de PlasSB (2025-01-15, 2025-01-16, 2025-01-17).
 * Para testear todo correctamente, hay q tener en marcha las 3 aplicaciones: Ecoembes, PlasSB y ContSocket.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.ecoembes.application",      // Clase principal
    "com.ecoembes.p1.controller",    // Controladores REST
    "com.AppServices",               // Servicios de aplicación
    "com.ServerState",               // Estado del servidor (tokens)
    "dao",                           // Repositorios (IMPORTANTE)
    "external",                      // Service Gateways (IMPORTANTE)
    "factory"                        // Factory para gateways (IMPORTANTE)
})

@EntityScan(basePackages = "com.entity")              // Entidades JPA
@EnableJpaRepositories(basePackages = "dao")          // Activar repositorios JPA
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}