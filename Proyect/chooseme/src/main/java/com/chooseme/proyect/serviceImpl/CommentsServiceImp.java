package com.chooseme.proyect.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chooseme.proyect.entities.Comments;
import com.chooseme.proyect.entities.Users;
import com.chooseme.proyect.repository.CommentsRepository;
import com.chooseme.proyect.repository.ProductsRepository;
import com.chooseme.proyect.repository.UsersRepository;
import com.chooseme.proyect.service.CommentsService;


@Service
public class CommentsServiceImp implements CommentsService{

	@Autowired
	UsersRepository userRepo;
	@Autowired
	CommentsRepository commentRepo;
	Comments comm;
	Users user;
	@Override
	public boolean newComment(Comments comment, String name) {
		
		int id;
		user = userRepo.getUserByUsername(name);
		
		try  {
			 id = user.getUser_id();
			 comment.setReviewerid(id);
			 comm = commentRepo.newReview(comment.getProduct_id(), comment.getReviewerid());
			 System.out.println(comm);
			 
		}
		catch (NullPointerException ne) {	
		}
		
		if(comm == null) {
			commentRepo.save(comment); 
			return true;
		 }else {
			 return false;
		 }
		
		
		
		
		
		
	}
	
	

}
