package com.chooseme.proyect.validator;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.chooseme.proyect.entities.Users;

import utils.Exceptions.ApiUnprocessableEntity;

@Component
public class UserValidatorComponent implements UserValidator {

	@Override
	public void validator(Users newusers) throws ApiUnprocessableEntity {
		
		
		/*
		 * validación de datos y mensajes de error
		 */
		
		
		if(newusers.getUser_name() == null || newusers.getUser_name().isEmpty()) {
			message("El nombre de usuario es obligatorio");
		}else if(newusers.getUser_name().length() <3) {
			message("El nombre de usuario debe tener al menos 3 caracteres");
		}
		if(newusers.getEmail() == null || newusers.getEmail().isEmpty()) {
			message("El correo es obligatorio");
		}else if(newusers.getEmail().length() <7) {
			message("El correo de usuario debe tener al menos 7 caracteres");
		}
		if(newusers.getName() == null || newusers.getName().isEmpty()) {
			message("El nombre es obligatorio");
		}else if(newusers.getName().length() <3) {
			message("El nombre debe tener al menos 3 caracteres");
		}
		if(newusers.getLastname() == null || newusers.getLastname().isEmpty()) {
			message("El apellido es obligatorio");
		}else if(newusers.getLastname().length() <3) {
			message("El apellido debe tener al menos 3 caracteres");
		}
		if(newusers.getPassword() == null || newusers.getPassword().isEmpty()) {
			message("ingrese una contraseña");
		}else if(!newusers.getPasstemp().equals(newusers.getPassword()) ) {
			message("Las contraseñas son diferentes");
		}
	}
	
	private void message(String message) throws ApiUnprocessableEntity {
		throw new ApiUnprocessableEntity(message);
	}






}
