package com.chooseme.proyect.serviceImpl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.chooseme.proyect.entities.Impressions;
import com.chooseme.proyect.entities.Users;
import com.chooseme.proyect.repository.ImpressionsRepository;
import com.chooseme.proyect.repository.UsersRepository;
import com.chooseme.proyect.service.ImpressionsService;


@Service
public class ImpressionsServiceImpl implements ImpressionsService{

	@Autowired
	UsersRepository userRepo;
	@Autowired
	ImpressionsRepository impressionRepo;
	Users user;

	@Override
	public void newImpression(Impressions newImpression, String name) {
		user = userRepo.getUserByUsername(name);
		if(newImpression != null) {
				newImpression.setUser_id(user.getUser_id());
				impressionRepo.save(newImpression); 
		}
	}
		
		
}
	

