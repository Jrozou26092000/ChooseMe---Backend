package com.chooseme.proyect.service;


import com.chooseme.proyect.dto.ProductsFilters;
import com.chooseme.proyect.entities.Products;

public interface ProductsService {

	public Iterable<Products> findUserByPName(ProductsFilters product);
	public Iterable<Products> findProductByCategory(ProductsFilters product);
	public Iterable<Products> findProductByScore(double stars_punctuation, double d);
	public Iterable<Products> findByDate(String timeStamp, String timeStamp2);

	//public Products saveProduct(Products newproduct);

}
