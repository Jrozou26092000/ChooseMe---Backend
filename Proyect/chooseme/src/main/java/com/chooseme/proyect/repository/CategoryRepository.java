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
	
	@Query("SELECT p.name FROM product_types pt INNER JOIN products p ON p.product_id = pt.product_id INNER JOIN types t ON pt.type_id = t.type_id WHERE t.type = :type")
	public Iterable<Products> getQuery(@Param("type") String type);
	
}
