package com.chooseme.proyect.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.chooseme.proyect.entities.Comments;
import com.chooseme.proyect.entities.Users;

public interface UsersRepository extends CrudRepository<Users, Integer> {


	
	@Query("SELECT u FROM Users u WHERE u.user_name = :username")
    public Users getUserByUsername(@Param("username") String username);
	
	@Query(value = "SELECT * FROM users as u "
			+ "WHERE u.user_name LIKE %:username%", nativeQuery = true)
	public Iterable<Users> getUsersByUsername(@Param("username") String username);
	
	@Query("SELECT u FROM Users u WHERE u.email = :email")
    public Users getUserByEmail(@Param("email") String username);

	@Query(value = "Select * from users "
			+ "Order by points DESC "
			+ "limit 5 ", nativeQuery = true)
	public Iterable<Users>gettop5();
	
	
	@Query(value = "Select * from users "
			+ "Where user_name LIKE %:name% "
			+ "and active = 1 "
			+ "Order by points DESC ", nativeQuery = true)
	public Page<Users> sortByName(String name, Pageable page);

	
	@Query("SELECT c FROM Users c "
			+ "WHERE c.user_id = :id")
	public Users getUserById(@Param ("id") int id);
	
	
	

	



	
}
