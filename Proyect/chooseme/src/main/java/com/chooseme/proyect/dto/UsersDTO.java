package com.chooseme.proyect.dto;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;

import javax.persistence.Column;

public class UsersDTO implements Serializable {
	
	private int user_id;
	
	private Blob user_photo;
	private String photo_url;
	private String user_name;
	private int active;
	private int points;
	private String google_account;
	private String name;
	private String lastname;
	public String token;
	
	
	public UsersDTO(int user_id, Blob user_photo, String photo_url, String user_name, int active, int points,
			String google_account, String name, String lastname, String token) {
		super();
		this.user_id = user_id;
		this.user_photo = user_photo;
		this.photo_url = photo_url;
		this.user_name = user_name;
		this.active = active;
		this.points = points;
		this.google_account = google_account;
		this.name = name;
		this.lastname = lastname;
		this.token = token;

	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Blob getUser_photo() {
		return user_photo;
	}
	public void setUser_photo(Blob user_photo) {
		this.user_photo = user_photo;
	}
	public String getPhoto_url() {
		return photo_url;
	}
	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public String getGoogle_account() {
		return google_account;
	}
	public void setGoogle_account(String google_account) {
		this.google_account = google_account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	
	
}
