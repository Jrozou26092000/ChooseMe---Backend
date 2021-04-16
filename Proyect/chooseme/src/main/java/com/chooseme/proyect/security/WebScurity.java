package com.chooseme.proyect.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.chooseme.proyect.serviceImpl.UsersServiceImpl;

@Configuration
@EnableWebSecurity
public class WebScurity extends WebSecurityConfigurerAdapter{

	/*private UsersService userService;
	
	public WebSecurity(UsersService userService) {
		this.userService = userService;
	}*/
	
}
