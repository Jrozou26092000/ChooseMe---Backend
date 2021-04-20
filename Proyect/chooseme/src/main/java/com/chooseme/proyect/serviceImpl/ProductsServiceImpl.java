package com.chooseme.proyect.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chooseme.proyect.entities.Products;
import com.chooseme.proyect.repository.ProductsRepository;
import com.chooseme.proyect.service.ProductsService;

@Service
public class ProductsServiceImpl implements ProductsService {
	@Autowired
	ProductsRepository productRepository;
	Iterable<Products> nameproductcheck;
	
	@Override
	public Iterable<Products> findUserByPName(Products product) {
		nameproductcheck = null;
		String name = product.getName();
		
		nameproductcheck =  productRepository.getProductByProductname(name);
		return nameproductcheck;
	}

	@Override
	public Products saveProduct(Products newproduct) {
		return productRepository.save(newproduct);
	}
	
	
	
}
