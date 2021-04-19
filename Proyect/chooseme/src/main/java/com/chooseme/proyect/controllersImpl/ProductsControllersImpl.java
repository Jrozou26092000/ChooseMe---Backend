package com.chooseme.proyect.controllersImpl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chooseme.proyect.controllers.ProductsController;
import com.chooseme.proyect.entities.Products;
import com.chooseme.proyect.service.ProductsService;

import utils.Exceptions.ApiUnprocessableEntity;


@RestController
public class ProductsControllersImpl implements ProductsController {
	
	@Autowired
	ProductsService productService;

	@Override
	@RequestMapping(value = "/products/findByName", produces = MediaType.APPLICATION_JSON_VALUE)
	public Products getProductByName(@RequestBody Products product) {
		return productService.findUserByPName(product);
	}
	

	@Override
	@PostMapping(value = "/products/add",  produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean addProducts(@RequestBody Products newproduct) {
		/*his.userValidator.validator(newproduct);*/
		productService.saveProduct(newproduct);
		return true;
	}
	
	/*
	@Override
	@PostMapping(value = "/products/review",  produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean addProducts(@RequestBody String review) {
		/*his.userValidator.validator(newproduct);
		productService.saveProduct(review);
		return true;
	}*/
	
	@Override
	@RequestMapping(value = "/products/test", method = RequestMethod.GET, produces = "application/json")
	public Boolean producttest() {
		return true;
	}
}
