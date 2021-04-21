package com.chooseme.proyect.service;

import java.util.Optional;

import com.chooseme.proyect.dto.ProductsFilters;
import com.chooseme.proyect.entities.Products;
import com.chooseme.proyect.entities.Users;

public interface ProductsService {

	public Iterable<Products> findUserByPName(ProductsFilters product);
	public Iterable<Products> findProductByCategory(ProductsFilters product);

	public Products saveProduct(Products newproduct);

}
