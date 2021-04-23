package com.chooseme.proyect.repository;




import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.chooseme.proyect.dto.ProductToFront;
import com.chooseme.proyect.entities.Products;

public interface ProductsRepository extends CrudRepository<ProductToFront, Integer> {
	
	@Query("SELECT p FROM Products p WHERE p.name = :productname")
    public Iterable<ProductToFront> getProductByProductname(@Param("productname") String productname);

	@Query(value = "SELECT p.product_id, p.brand, p.name, p.created_at, p.active, p.photo, p.price, p.verified, p.reviewer_id FROM product_types as pt "
			+ "JOIN products as p ON p.product_id = pt.product_id  "
			+ "JOIN types as t ON pt.type_id = t.type_id " 
			+ "WHERE t.type = :type ", nativeQuery  = true)
	public Iterable<ProductToFront> getQuery(@Param("type") String type);


	
	
}