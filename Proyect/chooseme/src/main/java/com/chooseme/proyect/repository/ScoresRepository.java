package com.chooseme.proyect.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.chooseme.proyect.entities.Scores;



public interface ScoresRepository extends CrudRepository<Scores, Integer>{
	
	@Query(value = 	"SELECT p.product_id, p.brand, p.name, p.created_at, p.active, p.photo, p.price, p.description, p.verified, p.reviewer_id, AVG(s.score) AS scoreAVG FROM product_types as pt "
			+ "JOIN products as p ON p.product_id = pt.product_id  "
			+ "JOIN types as t ON pt.type_id = t.type_id "
			+ "JOIN scores as s ON p.product_id = s.product_id "
			+ "WHERE t.type LIKE %:filter% "
			+ "OR p.name LIKE %:filter% "
			+ "GROUP BY p.product_id "
			+ "ORDER BY score DESC", nativeQuery  = true)
public Iterable<Scores> getQuery(@Param("filter") String filter);

	
	
}
