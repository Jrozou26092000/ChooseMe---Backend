package com.chooseme.proyect.validator;

import org.springframework.stereotype.Service;

import com.chooseme.proyect.entities.NewUsers;
import com.chooseme.proyect.entities.Users;

import utils.Exceptions.ApiUnprocessableEntity;

/*Interface to validate the create user data */

@Service
public interface UserValidator {


	void validator(Users newuser) throws ApiUnprocessableEntity;
}
