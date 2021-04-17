package com.chooseme.proyect.controllersImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.chooseme.proyect.controllers.UsersController;
import com.chooseme.proyect.entities.NewUsers;
import com.chooseme.proyect.entities.Users;
import com.chooseme.proyect.models.AuthenticationResponse;
import com.chooseme.proyect.service.UsersService;
import com.chooseme.proyect.util.JwtUtil;
import com.chooseme.proyect.validator.UserLogginValidator;
import com.chooseme.proyect.validator.UserValidatorComponent;

import utils.BCrypt;
import utils.Exceptions.ApiUnprocessableEntity;

@RestController
public class UsersControllerImpl implements UsersController {
	@Autowired
	UsersService userService;
	@Autowired
	UserValidatorComponent userValidator;
	@Autowired
	UserLogginValidator logginValidator;
	@Autowired
	JwtUtil jwtTokenUtil;
//	@Autowired
//	AuthenticationManager authenticationManager; 
	
	/*
	 * funciones provicionales para traer datos al front
	 * @RquestMapping genera la url de la cual se obtendrán los datos
	 * en postman se llama a la dirección como get
	 */
	// http://localhost:8888/users (GET)
	/*@RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
	@Override
	public List<Users> getUsers() {
		return userService.findAllUsers();
	}*/
	/*
	 * @PathVariable es provicional, y captura datos desde la url
	 * esto debe cambiar por un @RquestBody ya que permite insertar datos desde la url a cualquiera
	 * esto es una falla de seguridad que causa inyecciones sql.
	 */
	// http://localhost:8888/users/1 (GET)
	@Override
	@RequestMapping(value = "/users/findById", produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Users> getUsersById(@RequestBody Users user) {
		return userService.findUserById(user);
	}
	/*
	 * este permite capturar datos desde un body request raw json
	 * para ver la estructura, consultar la carpeta donde se encuentran los archivos de postman
	 */


	// http://localhost:8080/users/add (ADD)
	@Override
	@PostMapping(value = "/users/add",  produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean addUsers(@RequestBody Users newusers) throws ApiUnprocessableEntity {

		System.out.println(newusers.getPasstemp());
		System.out.println(newusers.getPassword());

		this.userValidator.validator(newusers);
		
		userService.saveUser(newusers);
		return true;

		
	}
	
	/*@Override
	@PostMapping(value = "/users/add",  produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean addUsers(@RequestBody Users user) throws ApiUnprocessableEntity {

		this.userValidator.validator(user);
		userService.saveUser(user);
		return false;
	}*/
	
	// http://localhost:8080/users/delete/1 (GET)
	@Override
	@PostMapping(value = "/users/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteUsers(@RequestBody Users user) {
        return userService.deleteUsers(user);
    }

	/*
	// http://localhost:8080/users/update (PATCH)
	@Override
	@RequestMapping(value = "/users/update", method = RequestMethod.PATCH, produces = "application/json")
	public String updateUsers(Users userNew) {
		return userService.updateUsers(userNew);
	}
	*/
	// http://localhost:8080/test (GET)
	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json")
	@Override
	public String test() {
		return "Test done";
	}
	
	
	@Override
	@RequestMapping(value = "/users/loggin",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> loggin(@RequestBody Users userNew) throws ApiUnprocessableEntity {
		
		this.logginValidator.validatorLoggin(userNew);
		if (!userService.logginUser(userNew)) {
			return null;
		}
//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userNew.getEmail(), userNew.getPassword()));
//		} catch (BadCredentialsException e) {
//			throw new Exception("Incorrect username or password", e);
//		} 
		
		Users user = userService.findUserByEmail(userNew).get();
		
		final String jwt = jwtTokenUtil.generateToken(new User(user.getUser_name(), user.getPassword(), new ArrayList<>()));
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));		
		
		
//		if(userService.logginUser(userNew)) {
//
//			System.out.println("password correcta");
//			return true;
//		}
//		
//		else {
//			System.out.println("password incorrecta");
//			return false;
//		}
		
	}
	@Override
	public List<Users> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String updateUsers(Users usersNew) {
		// TODO Auto-generated method stub
		return null;
	}
	
}