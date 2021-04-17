package com.chooseme.proyect.validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.chooseme.proyect.entities.Products;
import com.chooseme.proyect.service.UsersService;

import utils.Exceptions.ApiUnprocessableEntity;

public class ProductsValidatorComponent implements ProductsValidator{

	@Autowired
	UsersService userService;
	@Override
	public Boolean ProudctValidator(Products newproduct) throws ApiUnprocessableEntity {
		
		
		if(userService.ifFindUserById(newproduct.getReviewer_id())) {
			return false;
		}
		
		
		return true;
	}
	

}
