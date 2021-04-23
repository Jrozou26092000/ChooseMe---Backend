package com.chooseme.proyect.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "tokens")
public class Tokens {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="token_id")
	private int token_id;
	@Column(name= "userstoken")
	private String token;
	
	public int getToken_id() {
		return token_id;
	}
	public void setToken_id(int token_id) {
		this.token_id = token_id;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
