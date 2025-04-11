package com.restApi.prueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com.restApi.prueba.http_errors")
//@ComponentScan(basePackages = {"com.restApi.prueba", "com.restApi.prueba.http_errors"}) // Asegúrate de que el paquete esté incluido

public class PruebaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaApplication.class, args);
	}

}
