package com.chooseme.proyect.serviceImpl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.stereotype.Service;

import com.chooseme.proyect.dto.UsersDTO;
import com.chooseme.proyect.entities.Users;	
import com.chooseme.proyect.repository.UsersRepository;	
import com.chooseme.proyect.service.UsersService;
import com.chooseme.proyect.util.JwtUtil;
import com.chooseme.proyect.validator.UserLogginValidator;
import com.chooseme.proyect.validator.UserValidatorComponent;

import utils.BCrypt;
import utils.Exceptions.ApiUnprocessableEntity;


@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
	UsersRepository usersRepository;
	@Autowired
	JwtUtil jwtTokenUtil;
	@Autowired
	UserValidatorComponent logginValidator;
	
	
	Users user;
	Optional<Users> iduser_check;
	Users user_check;
	
	
	@Override
	public List<Users> findAllUsers() {
		return (List<Users>) usersRepository.findAll();
	}
	
	@Override
	public Optional<Users> findUserById(Users user) {
		iduser_check = null;
		int id = user.getUser_id();
		
		iduser_check =  usersRepository.findById( id);
		return iduser_check;
	}
	
	@Override
	public Boolean ifFindUserById(int id) {
		iduser_check = null;
		
		try {
			iduser_check =  usersRepository.findById( id);
		}
		catch(NoSuchElementException ne) {}
		catch(NullPointerException np) {}
		if(iduser_check == null) {
			return false;
		}else {
			return true;
		}
		
		
	}

	@Override
	public Optional<Users> findUserByName(String name) {
		
		Optional<Users> userbyname =  Optional.of(usersRepository.getUserByUsername(name));
		return userbyname;
	}
	
	@Override
	public Optional<Users> findUserByEmail(Users user) {
		String email = user.getEmail();
		Optional<Users> emailuser_check =  Optional.of(usersRepository.getUserByEmail(email));
		return emailuser_check;
	}

	@Override
	public Users saveUser(Users usersNew) {

		usersNew.setPassword(BCrypt.hashpw(usersNew.getPassword(), BCrypt.gensalt()));
		
		
		
		return usersRepository.save(usersNew);
	}
	

	@Override
    public Boolean deleteUsers(Users userDelete) {
        user = null;
        try {
            user = usersRepository.getUserByUsername(userDelete.getUser_name());
            if(BCrypt.checkpw(userDelete.getPassword(), user.getPassword()))            {
            	
            	user.setName(null);
            	user.setLastname(null);
            	user.setEmail(null);
            	user.setPassword(null);
            	user.setPhone(null);
                user.setActive(0);
                usersRepository.save(user);
                return true;
            }
        }
        catch(NoSuchElementException ne) {
        }

        return false;
    }
	
	
	@Override
	public Boolean updateUsers(Users usersUpdated) throws ApiUnprocessableEntity {

		Users oldUser = usersRepository.getUserByUsername(usersUpdated.getUser_name());
		if(oldUser != null) {
			if(BCrypt.checkpw(usersUpdated.getPassword(),oldUser.getPassword())){
				usersUpdated.setPassword(usersUpdated.getPasstemp());
				Users usersToUpdate = oldUser;
				usersToUpdate.setUser_id(usersUpdated.getUser_id());
				usersToUpdate.setUser_name(usersUpdated.getUser_name());
				usersToUpdate.setPassword(BCrypt.hashpw(usersUpdated.getPasstemp(), BCrypt.gensalt()));
				usersToUpdate.setActive(usersUpdated.getActive());
				usersToUpdate.setPoints(usersUpdated.getPoints());
				usersToUpdate.setGoogle_account(usersUpdated.getGoogle_account());	
				usersToUpdate.setName(usersUpdated.getName());
				usersToUpdate.setLastname(usersUpdated.getLastname());
				if(logginValidator.updateValidator(usersToUpdate)) {				
					usersRepository.save(usersToUpdate);
					return true;
				}				
			}	
		}
		return false;
	}	

	@Override
	public Boolean logginUser(Users usersNew) {
		
		user_check = null;

		try {
			user_check = usersRepository.getUserByEmail(usersNew.getEmail());
		}
		
		catch(NoSuchElementException ne) {
		}
		user_check.setActive(1);
        usersRepository.save(user_check);
		if(BCrypt.checkpw(usersNew.getPassword(), user_check.getPassword()))
		{
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public Boolean desactivateUser(Users userDesc) {
		user = null;
        try {
            user = usersRepository.getUserByUsername(userDesc.getUser_name());
            if(BCrypt.checkpw(userDesc.getPassword(), user.getPassword()))            {
            	
                user.setActive(0);
                usersRepository.save(user);
                return true;
            }
        }
        catch(NoSuchElementException ne) {
        }

        return false;
	}
	
	@Override
	public Optional<UsersDTO> getPerfil(String token) {
		Users user = null;
		UsersDTO userDTO = null;
		try {
			String name = jwtTokenUtil.extractUsername(token);
			user = findUserByName(name).get();
			userDTO = new UsersDTO(user.getUser_id(),user.getUser_photo(), user.getUser_photo_url(),user.getUser_name(),
					user.getActive(),user.getPoints(), user.getGoogle_account(), user.getName(), user.getLastname(), token);	
		}catch(NullPointerException e){			
		}
	   return Optional.ofNullable(userDTO);
	}
	
	
	
}
