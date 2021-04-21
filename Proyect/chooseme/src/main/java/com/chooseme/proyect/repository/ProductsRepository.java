package com.chooseme.proyect.repository;




import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.chooseme.proyect.entities.Products;

public interface ProductsRepository extends CrudRepository<Products, Integer> {
	
	@Query("SELECT p FROM Products p WHERE p.name = :productname")
    public Iterable<Products> getProductByProductname(@Param("productname") String productname);

	/*
	@Query("SELECT u FROM Users u WHERE u.email = :email")
    public Products getUserByEmail(@Param("email") String username);*/
	
}