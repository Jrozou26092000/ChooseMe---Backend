package com.chooseme.proyect.repository;




import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.chooseme.proyect.entities.ProductToFront;
import com.chooseme.proyect.entities.Products;

public interface ProductsRepository extends CrudRepository<Products, Integer> {
	
	@Query("SELECT p FROM Products p WHERE p.name = :productname")
    public Iterable<Products> getProductByProductname(@Param("productname") String productname);

	@Query(value = "SELECT p.product_id, p.brand, p.name, p.created_at, p.active, p.photo, p.price, p.verified, p.reviewer_id, AVG(s.score) FROM product_types as pt "
			+ "JOIN products as p ON p.product_id = pt.product_id  "
			+ "JOIN types as t ON pt.type_id = t.type_id "
			+ "JOIN scores as s ON p.product_id = s.product_id "
			+ "WHERE t.type LIKE :type "
			+ "GROUP BY p.product_id", nativeQuery  = true)
	public Iterable<Products> getQuery(@Param("type") String type);
	
	


	
	
}