package com.chooseme.proyect.controllers;

import java.util.List;	
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.chooseme.proyect.entities.Users;

import utils.Exceptions.ApiUnprocessableEntity;

@CrossOrigin(origins = "*", maxAge = 3600)
/* declaración de las funciones usadas en el implement*/
public interface UsersController {
	
	//devuelve una lista de usuarios de tipo User
	public List<Users> getUsers();
	//busca un usuario por su id (user_id)
	public Optional<Users> getUsersById(Users user);
	//agrega un nuevo usuario a la db
	//boolean addUsers(NewUsers newuser) throws ApiUnprocessableEntity;
	//actualiza la informacion de usuario
	public String updateUsers(Users usersNew);
	//para testear la coneccion
	public String test();

	//borra un usuario, para realizar la operacion se requiere la contraseña del usuario
	Boolean deleteUsers(Users user);
	
	ResponseEntity<?> loggin(Users userNew) throws ApiUnprocessableEntity;
	boolean addUsers(Users newusers) throws ApiUnprocessableEntity;
	
	



}
