package com.chooseme.proyect.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chooseme.proyect.entities.Categories;
import com.chooseme.proyect.entities.Products_Categories;
import com.chooseme.proyect.repository.CategoryRepository;
import com.chooseme.proyect.service.CategoriesService;


@Service
public class CategoriesServiceImpl implements CategoriesService {

	@Autowired
	CategoryRepository categoryRepo;
	Products_Categories category;
	
	@Override
	public int categoryByName(String category) {
		
		Categories categories = categoryRepo.getType(category);
		int id = categories.getType_id();
		return id;
	}

}
