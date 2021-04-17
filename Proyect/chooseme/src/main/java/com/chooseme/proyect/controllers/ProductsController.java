package com.chooseme.proyect.controllers;

import java.util.Optional;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chooseme.proyect.entities.Products;

@CrossOrigin(origins = "*", maxAge = 3600)
public interface ProductsController {



	public Products getProductByName(Products product);





	public Boolean producttest();


	public boolean addProducts(Products newproducts);

}
