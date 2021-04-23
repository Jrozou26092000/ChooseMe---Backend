package com.chooseme.proyect.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Optional;

import com.chooseme.proyect.dto.ProductsFilters;
import com.chooseme.proyect.entities.ProductToFront;
import com.chooseme.proyect.entities.Products;
import com.chooseme.proyect.entities.Users;

public interface ProductsService {

	public Iterable<Products> findUserByPName(ProductsFilters product);
	public Iterable<Products> findProductByCategory(ProductsFilters product);
	public Iterable<Products> findProductByScore(double stars_punctuation, double d);
	public Iterable<Products> findByDate(String timeStamp, String timeStamp2);

	//public Products saveProduct(Products newproduct);

}
