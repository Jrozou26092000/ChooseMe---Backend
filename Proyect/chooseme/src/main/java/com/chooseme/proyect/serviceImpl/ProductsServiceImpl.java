package com.chooseme.proyect.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chooseme.proyect.dto.ProductsFilters;
import com.chooseme.proyect.entities.Products;
import com.chooseme.proyect.repository.ProductsRepository;
import com.chooseme.proyect.service.CategoriesService;
import com.chooseme.proyect.service.ProductsService;

import ch.qos.logback.core.filter.Filter;

@Service
public class ProductsServiceImpl implements ProductsService {
	@Autowired
	ProductsRepository productRepository;
	Iterable<Products> nameproductcheck;
	CategoriesService categoryService;
	Products product;
	@Override
	public Iterable<Products> findUserByPName(ProductsFilters filter) {
		
		nameproductcheck = null;
		String name = filter.getName();
						
		nameproductcheck =  productRepository.getProductByProductname(name);
		return nameproductcheck;
	}

	@Override
	public Products saveProduct(Products newproduct) {
		return productRepository.save(newproduct);
	}

	@Override
	public Iterable<Products> findProductByCategory(ProductsFilters filter) {
		
		nameproductcheck = null;
		String category = filter.getCategory();
		
		int id = categoryService.categoryByName(category);
		
		return null;
	}
	
	
	
}
