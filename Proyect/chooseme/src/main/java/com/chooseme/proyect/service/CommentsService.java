package com.chooseme.proyect.service;

import com.chooseme.proyect.dto.CommentsDTO;
import com.chooseme.proyect.entities.Comments;
import com.chooseme.proyect.entities.Likes;

public interface CommentsService {


	public boolean newComment(Comments comment, String name);

	public Iterable<Comments> findCommentsFiltered(CommentsDTO filter, int page);
	
	public Iterable<Comments> findByProductId(int id, int page);
	
	public Iterable<Comments> findByReviewerId(int id, int page);
	
	public boolean addNewLike(Likes like);

}
