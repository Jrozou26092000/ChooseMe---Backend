package com.chooseme.proyect.service;

import java.util.List;
import java.util.Optional;

import com.chooseme.proyect.dto.UsersDTO;
import com.chooseme.proyect.entities.Users;

import utils.Exceptions.ApiUnprocessableEntity;
/*
 * interface para la implementación de la clase service
 */
public interface UsersService {
	// Trae todos los usuarios desde la DB
	public List<Users> findAllUsers();
	// Busca un usuario por su id
	public Optional<Users> findUserById(Users user);
	// Busca un usuario por su correo
	public Optional<Users> findUserByEmail(Users user);
	// Crea un nuevo usuario de tipo usuario
	//public Users saveUser(Users usersNew);
	// Actrualiza el usuario existente
	public Boolean updateUsers(Users userNew, String name) throws ApiUnprocessableEntity;

	//borra un usuario ingresando su contraseña y su id


	public Boolean logginUser(Users userNew);
	public Boolean deleteUsers(Users userDelete);
	public Users saveUser(Users usersNew);
	public Boolean desactivateUser(Users user);
	public Optional<Users> findUserByName(String name);
	public Boolean ifFindUserById(int id);
	public Optional<UsersDTO> getPerfil(String token);





}
