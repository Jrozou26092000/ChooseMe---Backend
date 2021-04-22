package com.chooseme.proyect.validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.chooseme.proyect.dto.ProductsFilters;
import com.chooseme.proyect.entities.Categories;
import com.chooseme.proyect.service.CategoriesService;

public class CategoriesValidatorComponent implements CategoriesValidator{
	@Autowired
	CategoriesService catService;
	public boolean Validator(ProductsFilters filters) {
		
		if(!filters.getCategory().isEmpty()) {
			
		}
		
		return false;
	}
	
	
}
