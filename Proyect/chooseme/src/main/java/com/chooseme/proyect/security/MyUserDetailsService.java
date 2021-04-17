package com.chooseme.proyect.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chooseme.proyect.entities.Users;
import com.chooseme.proyect.repository.UsersRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = usersRepository.getUserByUsername(username);
		return new User(user.getUser_name(), user.getPassword(), new ArrayList<>());
	}
	
}
