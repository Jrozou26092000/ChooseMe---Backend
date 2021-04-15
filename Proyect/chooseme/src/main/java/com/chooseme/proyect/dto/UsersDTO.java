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
	private String email;
	private String password;
	private int active;
	private String real_name;
	private String lastname;
	private String phone;
	private int points;
	private String google_account;
	private Timestamp created_at;
	private Timestamp modified_at;
}
