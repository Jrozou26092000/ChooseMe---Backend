package com.chooseme.proyect.service;

import com.chooseme.proyect.dto.CommentsDTO;
import com.chooseme.proyect.entities.Comments;
import com.chooseme.proyect.entities.Impressions;
import com.chooseme.proyect.entities.Likes;

public interface CommentsService {


	public boolean newComment(Comments comment, String name);

	
	public Iterable<Comments> findByProductId(int id, int page);
	
	public Iterable<Comments> findByReviewerId(int id, int page);
	
	public String addNewLike(Likes like);

	public boolean deleteComent(int id);

	public boolean update(Impressions impresion, String name);

}
