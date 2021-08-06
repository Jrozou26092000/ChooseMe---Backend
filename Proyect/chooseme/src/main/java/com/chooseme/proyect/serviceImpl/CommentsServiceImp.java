package com.chooseme.proyect.serviceImpl;


import java.util.NoSuchElementException;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import com.chooseme.proyect.entities.Comments;
import com.chooseme.proyect.entities.Impressions;
import com.chooseme.proyect.entities.Likes;
import com.chooseme.proyect.entities.Users;
import com.chooseme.proyect.repository.CommentsRepository;
import com.chooseme.proyect.repository.ImpressionsRepository;
import com.chooseme.proyect.repository.LikesRepository;
import com.chooseme.proyect.repository.UsersRepository;
import com.chooseme.proyect.service.CommentsService;



@Service
public class CommentsServiceImp implements CommentsService{

	@Autowired
	UsersRepository userRepo;
	@Autowired
	ImpressionsRepository imprRepo;
	@Autowired
	CommentsRepository commentRepo;
	@Autowired
	LikesRepository likesRepo;
	Comments comm;
	Users user;
	Impressions impression;
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
	
	
	
	@Override
	public String addNewLike(Likes like) {
		String res = "false";
		if (!userRepo.findById(like.getUser_id()).isEmpty() && !commentRepo.findById(like.getComment_id()).isEmpty()) {
			Optional<Likes> oldLike = likesRepo.getByUserAndComment(like.getUser_id(), like.getComment_id());
			Comments comment = commentRepo.findById(like.getComment_id()).get();
			Users reviewer = userRepo.findById(comment.getReviewer_id()).get();
			if (oldLike.isEmpty()) {
				Likes newLike = new Likes();
				newLike.setComment_id(like.getComment_id());
				newLike.setUser_id(like.getUser_id());
				newLike.setUp_down(like.getUp_down());
				likesRepo.save(newLike);
				if (like.getUp_down() == 1) {
					res = "Nuevo like";
					comment.setUps(comment.getUps()+1);
					reviewer.setPoints(reviewer.getPoints()+1);
				} else {
					res = "Nuevo dislike";
					comment.setDowns(comment.getDowns()+1);
					reviewer.setPoints(reviewer.getPoints()-1);
				}
			} else {
				Likes newLike = oldLike.get();
				if (like.getUp_down() == newLike.getUp_down()) {
					likesRepo.delete(newLike);
					if (like.getUp_down() == 1) {
						res = "Ya le había dado like";
						comment.setUps(comment.getUps()-1);
						reviewer.setPoints(reviewer.getPoints()-1);
					} else {
						res = "Ya le había dado dislike";
						comment.setDowns(comment.getDowns()-1);
						reviewer.setPoints(reviewer.getPoints()+1);
					}
				} else {
					newLike.setUp_down(like.getUp_down());
					likesRepo.save(newLike);
					if (like.getUp_down() == 1) {
						res = "Ya le había dado dislike";
						comment.setUps(comment.getUps()+1);
						comment.setDowns(comment.getDowns()-1);
						reviewer.setPoints(reviewer.getPoints()+2);
					} else {
						res = "Ya le había dado like";
						comment.setDowns(comment.getDowns()+1);
						comment.setUps(comment.getUps()-1);
						reviewer.setPoints(reviewer.getPoints()-2);
					}
				}
			}
			commentRepo.save(comment);
		}
		return res;
	}


	
	@Override
	public Iterable<Comments> findByProductId(int id, int page) {
		return  commentRepo.findByIdx(id, PageRequest.of(page, 10));
	}
	@Override
	public Iterable<Comments> findByReviewerId(int id, int page) {

		return commentRepo.findByIdy(id, PageRequest.of(page, 10));
	}
	
	Comments comment;
	
	
	
	public boolean deleteComent(int id) {
		impression = null;
		try {
			imprRepo.deleteCommentId(id);
			
			commentRepo.deleteById(id);
	        return true;
		}
		catch(NullPointerException np) {
			
			return false;
		}
		
	}



	public boolean update(Impressions impr, String name) {
		comment = null;

		int id = impr.getComment_id();
		comment = commentRepo.getCommentById(id);
		int idreviewer = comment.getReviewer_id();
		Users reviewer = userRepo.getUserById(idreviewer);	
		
		try {
			if(reviewer.getUser_name().equals(name)) {

				imprRepo.save(impr);
				return true;
				

			}
			else {
				System.out.println("no");
				return false;
			}
		}
		catch(NullPointerException np) {
			System.out.println("nullpointer");
			return false;
		}
		catch(NoSuchElementException ne) {
			return false;
		}
		
		

	}
	
	

}
