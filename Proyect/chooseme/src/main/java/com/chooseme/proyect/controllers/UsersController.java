package com.chooseme.proyect.controllers;

import java.util.List;	
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.chooseme.proyect.dto.CommentsDTO;
import com.chooseme.proyect.dto.UsersDTO;
import com.chooseme.proyect.entities.Users;

import utils.Exceptions.ApiUnprocessableEntity;

@CrossOrigin(origins = "*", maxAge = 3600)
public interface UsersController {
	
	//Devuelve una lista de usuarios de tipo User
	public List<Users> getUsers();
	
	//Busca un usuario por su id (user_id)
	public Optional<Users> getUsersById(Users user);
	
	//Agrega un nuevo usuario a la db
	public Boolean addUsers(Users newusers) throws ApiUnprocessableEntity;
	
	//Actualiza la informacion de usuario
	public ResponseEntity<?> updateUsers(Users usersNew, String Authorization) throws ApiUnprocessableEntity;
	
	//Borra un usuario, para realizar la operacion se requiere la contraseña del usuario
	public Boolean deleteUsers(Users user, String Authorization);
	
	//Realiza el login, para realizar la operacion se requiere la contraseña del usuario
	public ResponseEntity<?> loggin(Users userNew) throws ApiUnprocessableEntity;
	
	//Desactiva un usuario, para realizar la operacion se requiere la contraseña del usuario
	public Boolean desactivateUsers(Users user, String Autorization);
	
	//Devuelve la información del perfil del usuario
	public Optional<UsersDTO> perfil(String Authorization);	
	
	//Devuelve los 5 usuarios con mejor puntuacion
	public Iterable<UsersDTO> top5();
	
	//Encuentra los comentarios que ha hecho el usuario
	public Iterable<CommentsDTO> findCommentsByReviewerId(int id, int page);
	
	//Busca los usuarios por su nombre
	public Iterable<UsersDTO> searchByName(Users user, int page);

	//Logout de un usuario
	public Boolean logout(String Authorization);
		
	
	//Para testear la conexión
	public Boolean test();

	//Para testear la conexión
	public Boolean justTest();
	
}
