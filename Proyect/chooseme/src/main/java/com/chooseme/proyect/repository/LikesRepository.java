package com.chooseme.proyect.repository;


import java.util.Optional;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chooseme.proyect.entities.Likes;

@Repository
public interface LikesRepository extends CrudRepository<Likes, Integer> {
	
	@Query("SELECT l FROM Likes l "
			+ "WHERE l.like_id = :id")
	public Likes getById(@Param ("id") int id);
	
	@Query("SELECT l FROM Likes l "
			+ "WHERE l.user_id = :user_id AND l.comment_id = :comment_id")
	public Optional<Likes> getByUserAndComment(@Param ("user_id") int user_id, 
			@Param ("comment_id") int comment_id);
	
}