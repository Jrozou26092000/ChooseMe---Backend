package com.chooseme.proyect.repository;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import com.chooseme.proyect.entities.Products;

public interface ProductsRepository extends CrudRepository<Products, Integer> {
	
	@Query("SELECT p FROM Products p WHERE p.name = :productname")
    public Iterable<Products> getProductByProductname(@Param("productname") String productname);

	@Query(value = 	"SELECT p.product_id, p.brand, p.name, p.created_at, p.active, p.photo, p.price, p.description, p.verified, p.reviewer_id, AVG(s.score) AS score FROM product_types as pt "
					+ "JOIN products as p ON p.product_id = pt.product_id  "
					+ "JOIN types as t ON pt.type_id = t.type_id "
					+ "JOIN scores as s ON p.product_id = s.product_id "
					+ "WHERE t.type LIKE %:filter% "
					+ "OR p.name LIKE %:filter% "
					+ "GROUP BY p.product_id "
					+ "ORDER BY score DESC", nativeQuery  = true)
	public Iterable<Products> getQuery(@Param("filter") String filter);
	
	@Query(value = "SELECT p.product_id, p.brand, p.name, p.created_at, p.active, p.photo, p.price, p.description, p.verified, p.reviewer_id, AVG(s.score) AS score FROM product_types as pt "
			+ "JOIN products as p ON p.product_id = pt.product_id  "
			+ "JOIN types as t ON pt.type_id = t.type_id "
			+ "JOIN scores as s ON p.product_id = s.product_id "
			+ "GROUP BY p.product_id "
			+ "HAVING score >= :num "
			+ "and score < :num2 "
			+ "ORDER BY score DESC", nativeQuery  = true)
	public Iterable<Products> getByScore(@Param("num") double num, @Param("num2") double num2);
	
	@Query(value = "SELECT p.product_id, p.brand, p.name, p.created_at, p.active, p.photo, p.price, p.verified, p.description, p.reviewer_id, AVG(s.score) AS score FROM product_types as pt "
			+ "JOIN products as p ON p.product_id = pt.product_id  "
			+ "JOIN types as t ON pt.type_id = t.type_id "
			+ "JOIN scores as s ON p.product_id = s.product_id "
			+ "WHERE p.create_at "
			+ "GROUP BY p.product_id "
			+ "ORDER BY score DESC", nativeQuery  = true)
	public Iterable<Products> getByDate(@Param ("date") String create_atStar, @Param ("date") String create_atEnd);
	
	


	
	
}