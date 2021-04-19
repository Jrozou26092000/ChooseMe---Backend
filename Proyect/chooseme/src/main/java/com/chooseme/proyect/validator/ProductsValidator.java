package com.chooseme.proyect.validator;

import org.springframework.stereotype.Service;

import com.chooseme.proyect.entities.Products;

import utils.Exceptions.ApiUnprocessableEntity;

@Service
public interface ProductsValidator {
	public Boolean ProudctValidator(Products newproduct) throws ApiUnprocessableEntity;
}
