package com.chooseme.proyect.validator;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.stereotype.Component;
import com.chooseme.proyect.entities.Users;
import com.chooseme.proyect.repository.UsersRepository;
import com.chooseme.proyect.service.UsersService;

import utils.Exceptions.ApiUnprocessableEntity;

@Component
public class UserValidatorComponent implements UserValidator {
	@Autowired
	UsersService userService;
	UsersRepository userRepository;
	Optional <Users> user;
	Optional <Users> user2;
	
	/*
	 * validación de datos y mensajes de error
	 */

	@Override
	public Boolean validator(Users newusers) throws ApiUnprocessableEntity {

		if(newusers.getUser_name() == null || newusers.getUser_name().isEmpty()) {
			return false;
		}else {
			user = null;
			try {
				user = userService.findUserByName(newusers.getUser_name());
				if(!(user == null)) {
					System.out.println("Nombre de usuario ya existe");
					return false;
				}
			}
			catch(NoSuchElementException ne) {
				
			}
			catch(NullPointerException np) {
				
			}
		}
		if(newusers.getEmail() == null || newusers.getEmail().isEmpty()) {
			return false;
			
		} else {
			try {
				user2 = null;
				user2 = userService.findUserByEmail(newusers);
				if(!(user2==null)) {
					return false;
				}else {			
					String regexemail = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"; 
					Pattern patternemail = Pattern.compile(regexemail);
					Matcher matcher = patternemail.matcher(newusers.getEmail());
					if(!matcher.matches()) {
						System.out.println("formato de correo no valido");
						return false;
					}
				}
			}
			catch(NullPointerException ne) {
			}
		}
		if(newusers.getName() == null || newusers.getName().isEmpty()) {
			System.out.println("nombre obligatorio");
			return false;
		}else { 
			String regexname = "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$";
			Pattern patternname =Pattern.compile(regexname);
			Matcher matcher = patternname.matcher(newusers.getName());
			if(!matcher.matches()) {
				System.out.println("nombre muy largo o formato invalido");
				return false;
			}
		}
		
		
		if(newusers.getLastname() == null || newusers.getLastname().isEmpty()) {
			return false;
		}else {
			String regexlastname = "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$";
			Pattern patternlastname =Pattern.compile(regexlastname);
			Matcher matcher = patternlastname.matcher(newusers.getLastname());
			if(!matcher.matches()) {
				System.out.println("Nombre muy largo o formato invalido");
				return false;
		}
			
		}
		if(newusers.getPassword() == null || newusers.getPassword().isEmpty()) {
			System.out.println("password necesaria");
			return false;
		}else if(!newusers.getPasstemp().equals(newusers.getPassword()) ) {
			System.out.println("las contraseñas no coinciden");
			return false;
		}
		
		if(newusers.getPhone() == null || newusers.getPhone().isEmpty()) {
			System.out.println("campo numero vacio");
			return false;
			
		}else {
			String regexphone = "([3])+[0-9]{9}$";
			Pattern patternphone =Pattern.compile(regexphone);
			Matcher matcher = patternphone.matcher(newusers.getPhone());
			if(!matcher.matches()) {
				System.out.println("Formato de celular invalido o no colombiano");
				return false;
			}
		}
		
		System.out.println("entrada perfecta");
		return true;
	}
	
	@Override
	public Boolean updateValidator(Users newusers) throws ApiUnprocessableEntity {

		if(newusers.getUser_name() == null || newusers.getUser_name().isEmpty()) {
			System.out.println("username obligatorio");
			return false;
		}
		
		if(newusers.getEmail() == null || newusers.getEmail().isEmpty()) {
			System.out.println("email obligatorio");
			return false;
			
		}
		
		if(newusers.getName() == null || newusers.getName().isEmpty()) {
			System.out.println("nombre obligatorio");
			return false;
		}else { 
			String regexname = "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$";
			Pattern patternname =Pattern.compile(regexname);
			Matcher matcher = patternname.matcher(newusers.getName());
			if(!matcher.matches()) {
				System.out.println("nombre muy largo o formato invalido");
				return false;
			}
		}
		
		
		if(newusers.getLastname() == null || newusers.getLastname().isEmpty()) {
			System.out.println("apellido obligatorio");
			return false;
		}else {
			String regexlastname = "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$";
			Pattern patternlastname =Pattern.compile(regexlastname);
			Matcher matcher = patternlastname.matcher(newusers.getLastname());
			if(!matcher.matches()) {
				System.out.println("Nombre muy largo o formato invalido");
				return false;
		}
			
		}
		if(newusers.getPassword() == null || newusers.getPassword().isEmpty()) {
			System.out.println("password necesaria");
			return false;
		} 		
		
		if(newusers.getPhone() == null || newusers.getPhone().isEmpty()) {
			System.out.println("campo numero vacio");
			return false;
			
		}else {
			String regexphone = "([3])+[0-9]{9}$";
			Pattern patternphone =Pattern.compile(regexphone);
			Matcher matcher = patternphone.matcher(newusers.getPhone());
			if(!matcher.matches()) {
				System.out.println("Formato de celular invalido o no colombiano");
				return false;
			}
		}
		
		System.out.println("entrada perfecta");
		return true;
	}
	
	private void message(String message) throws ApiUnprocessableEntity {
		throw new ApiUnprocessableEntity(message);
	}






}
