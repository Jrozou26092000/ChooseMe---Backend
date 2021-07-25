package com.chooseme.proyect.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "likes")
public class Likes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "like_id")
	private int like_id;
	
	@Column(name = "user_id")
	private int user_id;
	
	@Column(name = "comment_id")
	private int comment_id;
	
	@Column(name = "up_down")
	private int up_down;

	public int getLike_id() {
		return like_id;
	}

	public void setLike_id(int like_id) {
		this.like_id = like_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public int getUp_down() {
		return up_down;
	}

	public void setUp_down(int up_down) {
		this.up_down = up_down;
	}
	
	
	
}
