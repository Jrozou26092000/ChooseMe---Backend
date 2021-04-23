package com.chooseme.proyect.service;

import java.util.Optional;

import com.chooseme.proyect.dto.ProductToFront;
import com.chooseme.proyect.dto.ProductsFilters;
import com.chooseme.proyect.entities.Products;
import com.chooseme.proyect.entities.Users;

public interface ProductsService {

	public Iterable<ProductToFront> findUserByPName(ProductsFilters product);
	public Iterable<ProductToFront> findProductByCategory(ProductsFilters product);

	//public Products saveProduct(Products newproduct);

}
