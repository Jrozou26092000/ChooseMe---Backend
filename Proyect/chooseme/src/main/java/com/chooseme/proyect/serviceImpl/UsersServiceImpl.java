package com.chooseme.proyect.serviceImpl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.stereotype.Service;

import com.chooseme.proyect.entities.NewUsers;
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
	public Users saveUser(Users usersNew) {
		//Users usersToUpdate = new Users();
		usersNew.setPassword(BCrypt.hashpw(usersNew.getPassword(), BCrypt.gensalt()));
		/*usersToUpdate.setUser_id(usersNew.getUser_id());
		usersToUpdate.setUser_name(usersNew.getUser_name());
		usersToUpdate.setEmail(usersNew.getEmail());
		usersToUpdate.setPassword(usersNew.getPassword());
		usersToUpdate.setActive(usersNew.getActive());
		usersToUpdate.setName(usersNew.getName());
		usersToUpdate.setLastname(usersNew.getLastname());
		usersToUpdate.setPhone(usersNew.getPhone());
		usersToUpdate.setPoints(usersNew.getPoints());
		usersToUpdate.setGoogle_account(usersNew.getGoogle_account());
		*/
		
		
		
		return usersRepository.save(usersNew);
	}
	

	@Override
    public Boolean deleteUsers(Users userDelete) {
        user = null;
        try {
            user = usersRepository.getUserByUsername(userDelete.getUser_name());
            if(BCrypt.checkpw(userDelete.getPassword(), user.getPassword()))            {
                System.out.print("Entramos al if");
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
