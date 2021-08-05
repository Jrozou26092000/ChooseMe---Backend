package com.chooseme.proyect.dto;


import java.sql.Timestamp;

import com.chooseme.proyect.entities.Impressions;



public class ImpressionsDTO {
	
	private int impression_id;
	private int comment_id;
	private Timestamp created_at;
	private int reviewer_id;
	private String user_name;
	private String impression;
	

	
	
	public ImpressionsDTO(int impression_id, int comment_id, Timestamp created_at, int reviewer_id, String user_name,
			String impression) {
		super();
		this.impression_id = impression_id;
		this.comment_id = comment_id;
		this.created_at = created_at;
		this.reviewer_id = reviewer_id;
		this.user_name = user_name;
		this.impression = impression;
	}

	public ImpressionsDTO(Impressions imp) {
		super();
		this.impression_id = imp.getImpression_id();
		this.comment_id = imp.getComment().getCommentId();
		this.created_at = imp.getCreated_at();
		this.reviewer_id = imp.getUser().getUser_id();
		this.user_name = imp.getUser().getUser_name();
		this.impression = imp.getImpression();
	}

	public int getImpression_id() {
		return impression_id;
	}

	public void setImpression_id(int impression_id) {
		this.impression_id = impression_id;
	}

	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public int getReviewer_id() {
		return reviewer_id;
	}

	public void setReviewer_id(int reviewer_id) {
		this.reviewer_id = reviewer_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getImpression() {
		return impression;
	}

	public void setImpression(String impression) {
		this.impression = impression;
	}
	
	
	
	
	
}
