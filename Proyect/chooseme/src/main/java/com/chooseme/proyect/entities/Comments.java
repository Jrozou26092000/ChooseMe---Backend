package com.chooseme.proyect.entities;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.chooseme.proyect.dto.UsersDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	@Column(name = "score")
	private int score;
	
	@Column(name = "up_down")
	private int up_down;
	
	@Column(name = "product_id")
	private int product_id;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reviewer_id", referencedColumnName = "user_id", insertable = false, updatable = false)
	Users user;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
	Products product;

	
	
	
	

	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
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
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getUp_down() {
		return up_down;
	}
	public void setUp_down(int up_down) {
		this.up_down = up_down;
	}
	
	@PrePersist
    public void prePersist() {
        Date date = new Date();
        long time = date.getTime();
        this.created_at = new Timestamp(time);
	}
}
