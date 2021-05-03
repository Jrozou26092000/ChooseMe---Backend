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
	public Boolean validatorLoggin(Users user) throws ApiUnprocessableEntity {
		try {
			user_check = usersRepository.getUserByEmail(user.getEmail());
		}
		catch (NullPointerException np) {
			
		}
        if(user_check == null) {
            return false;
        }
        else if(user.getEmail() == null || user.getEmail().isEmpty()) {
            return false;
        }
        else {
            return true;
        }
	}

	private void message(String message) throws ApiUnprocessableEntity {
		throw new ApiUnprocessableEntity(message);
		
	}

}
