package com.chooseme.proyect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;


@Configuration
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class ChoosemeApplication {

	public static void main(String[] args) {
		
		
		/*inicia la aplicación*/
		SpringApplication.run(ChoosemeApplication.class, args);
		
		
		
	}

}
