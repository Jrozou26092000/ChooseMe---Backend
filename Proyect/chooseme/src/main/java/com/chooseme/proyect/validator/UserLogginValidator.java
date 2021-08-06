package com.chooseme.proyect.validator;

import org.springframework.stereotype.Service;

import com.chooseme.proyect.entities.Users;

import utils.Exceptions.ApiUnprocessableEntity;

@Service
public interface UserLogginValidator {


	Boolean validatorLoggin(Users userNew) throws ApiUnprocessableEntity;
}
