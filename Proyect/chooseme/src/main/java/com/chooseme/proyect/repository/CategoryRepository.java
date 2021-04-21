package com.chooseme.proyect.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.chooseme.proyect.entities.Categories;
import com.chooseme.proyect.entities.Products;


public interface CategoryRepository extends CrudRepository<Categories, Integer>{

	
	@Query("SELECT t FROM types t WHERE t.type = :type")
    public Categories getType(@Param("type") String type);
	

	
}
