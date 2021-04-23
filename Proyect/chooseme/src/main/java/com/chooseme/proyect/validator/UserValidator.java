package com.chooseme.proyect.validator;

import org.springframework.stereotype.Service;


import com.chooseme.proyect.entities.Users;

import utils.Exceptions.ApiUnprocessableEntity;

/*Interface to validate the create user data */

@Service
public interface UserValidator {


	public Boolean validator(Users newuser) throws ApiUnprocessableEntity;

	Boolean updateValidator(Users newusers) throws ApiUnprocessableEntity;
}
