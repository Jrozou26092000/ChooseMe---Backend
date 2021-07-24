package com.chooseme.proyect.serviceImpl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chooseme.proyect.dto.CommentsDTO;
import com.chooseme.proyect.dto.UsersDTO;
import com.chooseme.proyect.entities.Comments;
import com.chooseme.proyect.entities.Users;	
import com.chooseme.proyect.repository.UsersRepository;	
import com.chooseme.proyect.service.UsersService;
import com.chooseme.proyect.util.JwtUtil;
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
    public Boolean deleteUsers(Users userDelete, String name) {
        user = null;
        try {
            user = usersRepository.getUserByUsername(name);
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
        catch(NullPointerException np) {
        	
        }

        return false;
    }
	
	
	@Override
	public String updateUsers(Users usersUpdated, String name, String newToken) throws ApiUnprocessableEntity {

		Users oldUser = usersRepository.getUserByUsername(name);
		if(oldUser != null) {
			if(BCrypt.checkpw(usersUpdated.getPassword(),oldUser.getPassword())
					&& oldUser.getUser_id() == usersUpdated.getUser_id()){
				
				Users usersToUpdate = oldUser;
				
				if(usersUpdated.getPasstemp()!= null && !(usersUpdated.getPasstemp().isBlank())) {
					usersUpdated.setPassword(usersUpdated.getPasstemp());
					usersToUpdate.setPassword(BCrypt.hashpw(usersUpdated.getPasstemp(), BCrypt.gensalt()));
				}				
				
				
				if (usersUpdated.getUser_name()!= null && !(usersUpdated.getUser_name().isBlank())) {
					
					Users temp = null;
                    try {
                        temp = findUserByName(usersUpdated.getUser_name()).get();
                    } catch (NullPointerException e) {}
                    if (temp !=null && !usersUpdated.getUser_name().equals(oldUser.getUser_name())){
                        return "false";
                    }
                    usersToUpdate.setUser_name(usersUpdated.getUser_name());
				}
				
				if (usersUpdated.getName()!= null && !(usersUpdated.getName().isBlank())) {
					usersToUpdate.setName(usersUpdated.getName());
				}
				
				if (usersUpdated.getLastname()!= null && !(usersUpdated.getLastname().isBlank())) {
					usersToUpdate.setLastname(usersUpdated.getLastname());
				}
				
				if(logginValidator.updateValidator(usersToUpdate)) {				
					usersRepository.save(usersToUpdate);
					return newToken;
				}				
			}	
		}
		return "false";
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
	

	@Override
	public Iterable<UsersDTO> getTop5(){
		Iterable<Users> user = null;
		Collection<UsersDTO> userDTO  = new HashSet<UsersDTO>();
		try {
			user = usersRepository.gettop5();
			user.forEach((c) ->{
				userDTO.add(new UsersDTO(c));
			});
		}
		catch(NullPointerException e) {

		}
		return userDTO;
		
	}
	

	@Override
	public Iterable<UsersDTO> sortByName(Users sortuser, int page) {
		Iterable<Users> user = null;
		Collection<UsersDTO> userDTO  = new HashSet<UsersDTO>();
		try {
			user = usersRepository.sortByName(sortuser.getUser_name(), page);
			user.forEach((c) ->{
				userDTO.add(new UsersDTO(c));
			});
		}
		catch(NullPointerException e) {

		}
		return userDTO;
		
		
	}

	@Override
	public boolean equalPassword(Users newuser, String name) {
		user = null;
        try {
            user = usersRepository.getUserByUsername(name);
            if(BCrypt.checkpw(newuser.getPassword(), user.getPassword())){

                return true;
            }
            else {
            	return false;
            }
        }
        catch(NoSuchElementException ne) {
        }
        return false;

	}
	
}
