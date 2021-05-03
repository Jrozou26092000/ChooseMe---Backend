package com.chooseme.proyect.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.chooseme.proyect.dto.UsersDTO;

@Entity
@Table(name = "comments")
public class Comments {

	@Id
	@Column(name = "comment_id")
	private int comment_id;
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "created_at")
	private Timestamp created_at;
	
	
	@Column(name = "reviewer_id")
	private int reviewer_id;

	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reviewer_id", insertable = false, updatable = false)
	Users user;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", insertable = false, updatable = false)
	Products product;
	
	/*@Transient
	private UsersDTO userdto;
	
	
	@PostPersist
	public void newUsersDTO() {
		this.userdto = new UsersDTO(user.getUser_id(),user.getUser_photo(), user.getUser_photo_url(),user.getUser_name(),
				user.getActive(),user.getPoints(), user.getGoogle_account(), user.getName(), user.getLastname(), null);
	}*/
	
	public int getCommentId() {
		return comment_id;
	}
	public void setCommentId(int id) {
		this.comment_id = id;
	}
	
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	

	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public int getReviewerid() {
		return reviewer_id;
	}
	public void setReviewerid(int id) {
		this.reviewer_id = id;
	}
	
}
