package com.chooseme.proyect.entities;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "impressions")
public class Impressions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "impressions_id")
	private int impression_id;
	
	@Column(name = "impression")
	private String impression;
	
	@Column(name = "created_at")
	private Timestamp created_at;
	
	@Column(name = "modified_at")
	private Timestamp modified_at;
	
	@Column(name = "comment_id")
	private int comment_id;
	
	@Column(name = "user_id")
	private int user_id;
	
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comment_id", referencedColumnName = "comment_id", insertable = false, updatable = false)
	Comments comment;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
	Users user;


	public int getImpression_id() {
		return impression_id;
	}

	public void setImpression_id(int impression_id) {
		this.impression_id = impression_id;
	}

	public String getImpression() {
		return impression;
	}

	public void setImpression(String impression) {
		this.impression = impression;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Comments getComment() {
		return comment;
	}

	public void setComment(Comments comment) {
		this.comment = comment;
	}


	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public int getComment_id() {
		return comment_id;
	}


	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	@PrePersist
    public void prePersist() {
        Date date = new Date();
        long time = date.getTime();
        this.created_at = new Timestamp(time);
        this.modified_at = new Timestamp(time);
	}
	@PreUpdate
    public void preUpdate() {
    	 Date date = new Date();
         long time = date.getTime();
         this.modified_at = new Timestamp(time);
    }
}
