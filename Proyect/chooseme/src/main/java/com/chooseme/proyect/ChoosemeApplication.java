package com.chooseme.proyect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

import com.chooseme.proyect.entities.Users;
import com.chooseme.proyect.repository.UsersRepository;

@Configuration
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class ChoosemeApplication {

	public static void main(String[] args) {
		
		
		/*inicia la aplicación*/
		SpringApplication.run(ChoosemeApplication.class, args);
		
		
		
	}

}
