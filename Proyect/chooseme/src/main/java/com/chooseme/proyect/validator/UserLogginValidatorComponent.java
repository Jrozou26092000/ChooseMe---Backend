package com.chooseme.proyect.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chooseme.proyect.entities.Users;
import com.chooseme.proyect.repository.UsersRepository;

import utils.Exceptions.ApiUnprocessableEntity;

@Component
public class UserLogginValidatorComponent implements UserLogginValidator {
	@Autowired
	UsersRepository usersRepository;
	Users user_check;
	@Override
	public void validatorLoggin(Users user) throws ApiUnprocessableEntity {

		user_check = usersRepository.getUserByEmail(user.getEmail());

		if(user.getEmail() == null || user.getEmail().isEmpty()) {
			message("El correo es obligatorio");
		}else if(user_check.getEmail().isEmpty()) {
			message("No se encontro al usuario");
		}
		
		
	}

	private void message(String message) throws ApiUnprocessableEntity {
		throw new ApiUnprocessableEntity(message);
		
	}

}
