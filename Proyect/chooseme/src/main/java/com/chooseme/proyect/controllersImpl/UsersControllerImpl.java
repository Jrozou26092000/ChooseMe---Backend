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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.chooseme.proyect.controllers.UsersController;
import com.chooseme.proyect.entities.Products;
import com.chooseme.proyect.entities.Users;
import com.chooseme.proyect.models.AuthenticationResponse;
import com.chooseme.proyect.service.ProductsService;
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
	
	

	@RequestMapping(value = "/users/perfil", method = RequestMethod.POST, produces = "application/json")
	@Override
	public Optional<Users> perfil(@RequestHeader String Authorization) {
		String name = jwtTokenUtil.extractUsername(Authorization.substring(7));
	
		return userService.findUserByName(name);
	}

	@Override
	@RequestMapping(value = "/users/findById", produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Users> getUsersById(@RequestBody Users user) {
		return userService.findUserById(user);
	}
	/*
	 * este permite capturar datos desde un body request raw json
	 * para ver la estructura, consultar la carpeta donde se encuentran los archivos de postman
	 */

	@Override
	@PostMapping(value = "/users/add",  produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean addUsers(@RequestBody Users newusers) throws ApiUnprocessableEntity {


		if(this.userValidator.validator(newusers)) {
			userService.saveUser(newusers);
			return true;

		}else {
			return false;
		}
		
		
		
	}
	
	@Override
	@PostMapping(value = "/users/desactivate", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean desactivateUsers(@RequestBody Users user) {
        return userService.desactivateUser(user);
    }
	

	@Override
	@PostMapping(value = "/users/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteUsers(@RequestBody Users user) {
        return userService.deleteUsers(user);
    }

	// http://localhost:8080/test (GET)
	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json")
	@Override
	public Boolean test() {
		return true;
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
		//System.out.println(jwtTokenUtil.extractUsername(jwt));
		return ResponseEntity.ok(new AuthenticationResponse(jwt));		
		
	}
	
	@Override
	public List<Users> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String updateUsers(Users usersNew) {
		
		return null;
	}
	
	@Override
	@RequestMapping(value = "/users/test", method = RequestMethod.GET, produces = "application/json")
	public Boolean justtest() {
		return true;
	}



	
	
}