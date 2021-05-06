package com.chooseme.proyect.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.chooseme.proyect.entities.Users;

public interface UsersRepository extends CrudRepository<Users, Integer> {


	
	@Query("SELECT u FROM Users u WHERE u.user_name = :username")
    public Users getUserByUsername(@Param("username") String username);
	
	@Query("SELECT u FROM Users u WHERE u.email = :email")
    public Users getUserByEmail(@Param("email") String username);

	@Query(value = "Select * from users "
			+ "Order by points DESC "
			+ "limit 5 ", nativeQuery = true)
	public Iterable<Users>gettop5();
	
	
	@Query(value = "Select * from users "
			+ "Where user_name LIKE %:name% "
			+ "Order by points DESC "
			+ "LIMIT :page, 10 ", nativeQuery = true)
	public Iterable<Users> sortByName(String name, int page);


	
	

	



	
}
