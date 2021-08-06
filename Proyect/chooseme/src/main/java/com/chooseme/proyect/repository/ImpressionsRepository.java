package com.chooseme.proyect.repository;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.chooseme.proyect.entities.Impressions;

@Repository
public interface ImpressionsRepository extends CrudRepository<Impressions, Integer> {
	
	
	@Query("SELECT i FROM Impressions i "
			+ "WHERE i.impression_id = :id")
	public Iterable<Impressions> getById(@Param ("id") int id);


	
	@Query("SELECT i FROM Impressions i "
			+ "WHERE i.comment_id = :id ")
	public Page<Impressions> findByComment(@Param("id") int id, Pageable page);
	

	@Query("SELECT i FROM Impressions i "
			+ "WHERE i.user_id = :id ")
	public Page<Impressions> findByReviewer(@Param("id") int id, Pageable page);

	@Query(value = "SELECT * FROM impressions as i "
			+ "WHERE i.comment_id = :comment_id "
			+ "AND i.user_id = :user_id", nativeQuery = true)
	public Impressions newImpression (int comment_id, int user_id);
}
	
