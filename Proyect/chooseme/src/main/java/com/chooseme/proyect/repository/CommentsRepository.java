package com.chooseme.proyect.repository;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chooseme.proyect.entities.Comments;

@Repository
public interface CommentsRepository extends CrudRepository<Comments, Integer> {
	

	
	@Query("SELECT c FROM Comments c "
			+ "WHERE c.product_id = :id")
	public Iterable<Comments> getById(@Param ("id") int id);

	
	@Query("SELECT c FROM Comments c "
			+ "WHERE c.comment_id = :id")
	public Comments getCommentById(@Param ("id") int id);
	
	
	@Query("SELECT c FROM Comments c "
			+ "WHERE c.product_id = :id ")
	public Page<Comments> findByIdx(@Param("id") int id, Pageable page);
	
	@Query("SELECT c FROM Comments c "
			+ "WHERE c.reviewer_id = :id ")
	public Page<Comments> findByIdy(@Param("id") int id, Pageable page);

	
	@Query(value = "SELECT * FROM comments as c "
			+ "WHERE c.product_id = :product_id "
			+ "AND c.reviewer_id = :reviewer_id", nativeQuery = true)
	public Comments newReview(int product_id, int reviewer_id);
	
	@Query(value = "SELECT * FROM comments as c "
			+ "WHERE c.score = :score ", nativeQuery = true)
	public Iterable<Comments> findByScore(int score);
	
	@Query(value = "SELECT * FROM comments as c "
			+ "WHERE p.created_at BETWEEN :create_atStart AND :create_atEnd "
			, nativeQuery  = true)
		public Iterable<Comments> getByDate(@Param ("create_atStart") String create_atStart, @Param ("create_atEnd") String create_atEnd);

}