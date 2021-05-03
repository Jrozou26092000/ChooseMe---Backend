package com.chooseme.proyect.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.chooseme.proyect.entities.Comments;
import com.chooseme.proyect.entities.Products;

public interface CommentsRepository extends CrudRepository<Comments, Integer> {
	
	@Query(value = "SELECT c.* FROM comments as c "
			+ "JOIN users as u "
			+ "ON c.reviewer_id = u.user_id "
			+ "JOIN products as p "
			+ "ON p.product_id = c.product_id "
			+ "WHERE c.product_id = :id", nativeQuery  = true)
	public Iterable<Comments> getById(@Param ("id") int id);

	
	@Query(value = "SELECT c.* FROM comments as c "
			+ "JOIN users as u "
			+ "ON c.reviewer_id = u.user_id "
			+ "JOIN products as p "
			+ "ON p.product_id = c.product_id "
			+ "WHERE c.product_id = :id "
			+ "LIMIT :page, 10", nativeQuery  = true)
	public Iterable<Comments> findByIdx(int id, int page);

}