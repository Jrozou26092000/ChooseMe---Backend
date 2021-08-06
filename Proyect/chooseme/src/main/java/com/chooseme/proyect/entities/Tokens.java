package com.chooseme.proyect.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tokens")
public class Tokens {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="token_id")
	private int token_id;
	@Column(name= "userstoken")
	private String userstoken;
	
	public int getToken_id() {
		return token_id;
	}
	public void setToken_id(int token_id) {
		this.token_id = token_id;
	}
	
	public String getToken() {
		return userstoken;
	}
	public void setToken(String token) {
		this.userstoken = token;
	}
}
