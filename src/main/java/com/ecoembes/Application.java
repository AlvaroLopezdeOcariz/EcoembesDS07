package com.ecoembes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}


/**

 * Prueba en el navegador:

	* http://localhost:8080/ping → debe responder ecoembesDS07 OK.

	* http://localhost:8080/swagger-ui.html → debe cargar Swagger UI.

*/


/**

 * Añadir el API REST al proyecto y probar en el navegador:

	* com.ecoembes.p1.controller

	* com.ecoembes.p1.service

	* com.ecoembes.p1.dto

	* com.ecoembes.p1.exception
	* 
	* * com.ecoembes.p1.exception
*/