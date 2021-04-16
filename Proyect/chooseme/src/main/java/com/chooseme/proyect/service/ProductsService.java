package com.chooseme.proyect.service;

import java.util.Optional;

import com.chooseme.proyect.entities.Products;
import com.chooseme.proyect.entities.Users;

public interface ProductsService {

	public Products findUserByPName(Products product);

	public Products saveProduct(Products newproduct);

}
