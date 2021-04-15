package com.chooseme.proyect.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.chooseme.proyect.entities.NewUsers;
import com.chooseme.proyect.entities.Users;

public interface UsersRepository extends CrudRepository<Users, Integer> {


	
	@Query("SELECT u FROM Users u WHERE u.user_name = :username")
    public Users getUserByUsername(@Param("username") String username);
	
	@Query("SELECT u FROM Users u WHERE u.email = :email")
    public Users getUserByEmail(@Param("email") String username);
	




	
}
