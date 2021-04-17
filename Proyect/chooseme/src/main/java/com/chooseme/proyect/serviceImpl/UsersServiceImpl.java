package com.chooseme.proyect.serviceImpl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.stereotype.Service;

import com.chooseme.proyect.entities.Users;	
import com.chooseme.proyect.repository.UsersRepository;	
import com.chooseme.proyect.service.UsersService;

import utils.BCrypt;


@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
	UsersRepository usersRepository;
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
	public String updateUsers(Users usersUpdated) {
		
		int num = usersUpdated.getUser_id();
		if(usersRepository.findById( num).isPresent()) {
			Users usersToUpdate = new Users();
			usersToUpdate.setUser_id(usersUpdated.getUser_id());
			usersToUpdate.setUser_name(usersUpdated.getUser_name());
			usersToUpdate.setEmail(usersUpdated.getEmail());
			usersToUpdate.setPassword(usersUpdated.getPassword());
			usersToUpdate.setActive(usersUpdated.getActive());
			usersToUpdate.setName(usersUpdated.getName());
			usersToUpdate.setLastname(usersUpdated.getLastname());
			usersToUpdate.setPhone(usersUpdated.getPhone());
			usersToUpdate.setPoints(usersUpdated.getPoints());
			usersToUpdate.setGoogle_account(usersUpdated.getGoogle_account());
			
			usersRepository.save(usersToUpdate);
		}
		
		return "Error al modificar el usuario";
	}	

	@Override
	public Boolean logginUser(Users usersNew) {
		
		user_check = null;

		try {
			user_check = usersRepository.getUserByEmail(usersNew.getEmail());
		}
		
		catch(NoSuchElementException ne) {
		}
		if(BCrypt.checkpw(usersNew.getPassword(), user_check.getPassword()))
		{
			return true;
		}else {
			return false;
		}
		
	}
	
}
