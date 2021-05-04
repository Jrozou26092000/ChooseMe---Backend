package com.chooseme.proyect.repository;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.chooseme.proyect.entities.Comments;

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
	
	@Query(value = "SELECT c.* FROM comments as c "
			+ "JOIN users as u "
			+ "ON c.reviewer_id = u.user_id "
			+ "JOIN products as p "
			+ "ON p.product_id = c.product_id "
			+ "WHERE c.reviewer_id = :id "
			+ "LIMIT :page, 10", nativeQuery  = true)
	public Iterable<Comments> findByIdy(int id, int page);

	
	@Query("SELECT c FROM Comments  c "
			+ "JOIN c.users u "
			+ "ON c.reviewer_id = u.user_id "
			+ "WHERE c.product_id = :product_id "
			+ "AND c.reviewer_id = :reviewer_id")
	public Comments newReview(int product_id, int reviewer_id);

}//"SELECT u FROM Users u WHERE u.user_name = :username"