package com.chooseme.proyect.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.chooseme.proyect.entities.Tokens;

public interface TokensRepository extends CrudRepository<Tokens, Integer> {
	
	@Query("SELECT t FROM Tokens t WHERE t.userstoken = :token")
    public Tokens getTokenByToken(@Param("token") String token);
	
//	@Query("SELECT u FROM Users u WHERE u.user_name = :username")
//    public Users getUserByUsername(@Param("username") String username);
}
