package com.chooseme.proyect.repository;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import com.chooseme.proyect.entities.Products;

public interface ProductsRepository extends CrudRepository<Products, Integer> {
	
	@Query("SELECT p FROM Products p WHERE p.name = :productname")
    public Iterable<Products> getProductByProductname(@Param("productname") String productname);

	@Query(value = 	"SELECT p.product_id, p.brand, p.name, p.created_at, p.active, p.photo, p.price, p.description, p.verified, p.reviewer_id, AVG(c.score) AS score FROM product_types as pt "
					+ "JOIN products as p ON p.product_id = pt.product_id  "
					+ "JOIN types as t ON pt.type_id = t.type_id "
					+ "JOIN comments as c ON p.product_id = c.product_id "
					+ "WHERE t.type LIKE %:filter% "
					+ "OR p.name LIKE %:filter% "
					+ "GROUP BY p.product_id "
					+ "ORDER BY score DESC", nativeQuery  = true)
	public Iterable<Products> getQuery(@Param("filter") String filter);
	
	@Query(value = "SELECT p.product_id, p.brand, p.name, p.created_at, p.active, p.photo, p.price, p.description, p.verified, p.reviewer_id, AVG(c.score) AS score FROM product_types as pt "
			+ "JOIN products as p ON p.product_id = pt.product_id  "
			+ "JOIN types as t ON pt.type_id = t.type_id "
			+ "JOIN comments as s ON p.product_id = c.product_id "
			+ "GROUP BY p.product_id "
			+ "HAVING score >= :num "
			+ "and score < :num2 "
			+ "ORDER BY score DESC", nativeQuery  = true)
	public Iterable<Products> getByScore(@Param("num") double num, @Param("num2") double num2);
	
	@Query(value = "SELECT p.product_id, p.brand, p.name, p.created_at, p.active, p.photo, p.price, p.verified, p.description, p.reviewer_id, AVG(c.score) AS score FROM product_types as pt "
			+ "JOIN products as p ON p.product_id = pt.product_id  "
			+ "JOIN types as t ON pt.type_id = t.type_id "
			+ "JOIN comments as s ON p.product_id = c.product_id "
			+ "WHERE p.created_at BETWEEN :create_atStart AND :create_atEnd "
			+ "GROUP BY p.product_id "
			+ "ORDER BY score DESC", nativeQuery  = true)
	public Iterable<Products> getByDate(@Param ("create_atStart") String create_atStart, @Param ("create_atEnd") String create_atEnd);
	
	


	
	
}