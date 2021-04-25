package com.chooseme.proyect.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chooseme.proyect.dto.ProductsFilters;
import com.chooseme.proyect.entities.Products;
import com.chooseme.proyect.entities.Scores;
import com.chooseme.proyect.repository.CategoryRepository;
import com.chooseme.proyect.repository.ProductsRepository;
import com.chooseme.proyect.service.CategoriesService;
import com.chooseme.proyect.service.ProductsService;


@Service
public class ProductsServiceImpl implements ProductsService {
	@Autowired
	ProductsRepository productRepository;
	Iterable<Products> nameproductcheck;
	Iterable<Products> nameprod;
	CategoriesService categoryService;
	
	@Autowired
	CategoryRepository catRepo;
	Products product;
	Scores score;
	
	
	@Override
	public Iterable<Products> findUserByPName(ProductsFilters filter) {

		String name = filter.getName();
						
		nameproductcheck =  productRepository.getProductByProductname(name);
		
		return nameproductcheck;
	}

	@Override
	public Iterable<Products> findProductByCategory(ProductsFilters filter) {
		

		if(!(filter.getName() == null)) {
			String nameorcat = filter.getName();
			nameprod = productRepository.getQuery(nameorcat);
		}
		
		return nameprod;
	}
	@Override
	public Iterable<Products> findProductByScore(double stars_punctuation, double d) {
		
		
		return productRepository.getByScore(stars_punctuation, d);
	}
	@Override
	public Iterable<Products> findByDate(String create_atStart, String create_atEnd) {
		
		return  productRepository.getByDate(create_atStart, create_atEnd);
	}
	
	
	
}
