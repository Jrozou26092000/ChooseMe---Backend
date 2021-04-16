package com.chooseme.proyect.controllersImpl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.chooseme.proyect.controllers.UsersController;
import com.chooseme.proyect.entities.Users;
import com.chooseme.proyect.service.UsersService;
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
	public boolean addUsers(@RequestBody Users newusers) throws ApiUnprocessableEntity {

		System.out.println(newusers.getPasstemp());
		System.out.println(newusers.getPassword());

		this.userValidator.validator(newusers);
		
		userService.saveUser(newusers);
		return true;

		
	}
	

	@Override
	@PostMapping(value = "/users/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteUsers(@RequestBody Users user) {
        return userService.deleteUsers(user);
    }

	// http://localhost:8080/test (GET)
	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json")
	@Override
	public String test() {
		return "Test done";
	}
	
	
	@Override
	@RequestMapping(value = "/users/loggin",  produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean loggin(@RequestBody Users userNew) throws ApiUnprocessableEntity {
		
		if(this.logginValidator.validatorLoggin(userNew)) {
            if(userService.logginUser(userNew)) {
                System.out.println("password correcta");
                return true;
            }else {
                return false;
            }

        }else {
            return this.logginValidator.validatorLoggin(userNew);
        }
		
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