package com.chooseme.proyect.dto;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.chooseme.proyect.entities.Comments;
import com.chooseme.proyect.entities.Impressions;
import com.chooseme.proyect.entities.Products;
import com.chooseme.proyect.entities.Users;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



public class CommentsDTO implements Serializable {
	
	private int comment_id;
	private String comment;
	private String created_at;
	private int reviewer_id;
	private Integer score;
//	private int up_down;
	private int ups;
	private int downs;
	private int product_id;
	private String user_name;
	private String product_name;
	private int user_id;
	private Collection<ImpressionsDTO> impressions;
	
	public CommentsDTO(int comment_id, String comment, String created_at, int reviewer_id, int score, int ups,
			int downs, int product_id, String user_name, String product_name, int user_id) {
		super();
		this.comment_id = comment_id;
		this.comment = comment;
		this.created_at = created_at;
		this.reviewer_id = reviewer_id;
		this.score = score;
//		this.up_down = up_down;
		this.ups = ups;
		this.downs = downs;
		this.product_id = product_id;
		this.product_name = product_name;
		this.user_name = user_name;
		this.user_id = user_id;
	}
	
	public CommentsDTO(Comments comm) {
		super();
		this.comment_id = comm.getCommentId();
		this.comment = comm.getComment();
		this.created_at = comm.getCreated_at().toString();
		this.reviewer_id = comm.getReviewerid();
		this.score = comm.getScore();
//		this.up_down = comm.getUp_down();
		this.ups = comm.getUps();
		this.downs = comm.getDowns();
		this.product_id = comm.getProduct_id();
		this.user_name = comm.getUser().getUser_name();
		this.user_id = comm.getUser().getUser_id();
		this.product_name = comm.getProduct().getName();
		this.impressions = new HashSet<ImpressionsDTO>();
		for (Impressions imp : comm.getImpressions()) {
			this.impressions.add(new ImpressionsDTO(imp));
		}
		
	}
	
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public int getReviewer_id() {
		return reviewer_id;
	}
	public void setReviewer_id(int reviewer_id) {
		this.reviewer_id = reviewer_id;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
//	public int getUp_down() {
//		return up_down;
//	}
//	public void setUp_down(int up_down) {
//		this.up_down = up_down;
//	}
	
	public int getProduct_id() {
		return product_id;
	}
	
	public int getUps() {
		return ups;
	}

	public void setUps(int ups) {
		this.ups = ups;
	}

	public int getDowns() {
		return downs;
	}

	public void setDowns(int downs) {
		this.downs = downs;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public Collection<ImpressionsDTO> getImpressions() {
		return impressions;
	}

	public void setImpressions(Collection<ImpressionsDTO> impressions) {
		this.impressions = impressions;
	}
	
	
}
