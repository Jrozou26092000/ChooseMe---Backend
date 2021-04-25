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
import com.chooseme.proyect.dto.UsersDTO;
import com.chooseme.proyect.entities.Products;
import com.chooseme.proyect.entities.Tokens;
import com.chooseme.proyect.entities.Users;
import com.chooseme.proyect.models.AuthenticationResponse;
import com.chooseme.proyect.repository.TokensRepository;
import com.chooseme.proyect.repository.UsersRepository;
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


	@Autowired
	TokensRepository tokenRepo;
//	AuthenticationManager authenticationManager; 
	
	

	@RequestMapping(value = "/users/perfil", method = RequestMethod.POST, produces = "application/json")
	@Override
	public Optional<UsersDTO> perfil(@RequestHeader String Authorization) {	
		return userService.getPerfil(Authorization.substring(7));
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
    public Boolean desactivateUsers(@RequestBody Users user, @RequestHeader String Authorization) {
		String name = jwtTokenUtil.extractUsername(Authorization.substring(7));
		user.setUser_name(name);
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
		
		userNew.setActive(1);
		this.logginValidator.validatorLoggin(userNew);
		if (!userService.logginUser(userNew)) {
			return null;
		}

		
		Users user = userService.findUserByEmail(userNew).get();
		
		final String jwt = jwtTokenUtil.generateToken(new User(user.getUser_name(), user.getPassword(), new ArrayList<>()));
		Tokens token = new Tokens();
		token.setToken(jwt);
		tokenRepo.save(token);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));		
		
	}
	
	@Override
	public List<Users> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@RequestMapping(value = "/users/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateUsers(@RequestBody Users userNew, @RequestHeader String Authorization) throws ApiUnprocessableEntity {	
		String name = jwtTokenUtil.extractUsername(Authorization.substring(7));
		final String newToken = jwtTokenUtil.generateToken(new User(userNew.getUser_name(), userNew.getPassword(), new ArrayList<>()));
		Tokens token = tokenRepo.getTokenByToken(Authorization.substring(7));
		tokenRepo.delete(token);
		Tokens newTokens = new Tokens();
		newTokens.setToken(newToken);
		tokenRepo.save(newTokens);
		if (userNew.getPasstemp().equals(null)) {
			return ResponseEntity.badRequest().body("La nueva contraseña esta vacia");
		}
		return ResponseEntity.ok(userService.updateUsers(userNew, name, newToken));
	}
	
	@Override
	@RequestMapping(value = "/users/out", produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean out(@RequestHeader String Authorization) {
//		Tokens token = new Tokens();
//		String tokenNew = Authorization.substring(7);
//		token.setToken(tokenNew);
//		tokenRepo.save(token);
		Tokens token = tokenRepo.getTokenByToken(Authorization.substring(7));
//		if (token == null) {
//			return false;
//		}
		tokenRepo.delete(token);
		return true;
	}
	
	
	@Override
	@RequestMapping(value = "/users/test", method = RequestMethod.GET, produces = "application/json")
	public Boolean justtest() {
		return true;
	}



	
	
}