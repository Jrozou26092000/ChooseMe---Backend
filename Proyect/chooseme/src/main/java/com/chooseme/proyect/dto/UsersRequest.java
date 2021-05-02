package com.chooseme.proyect.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Data
public class UsersRequest implements Serializable{

	@JsonProperty("user_name")
	private String user_name;
	@JsonProperty("email")
	private String email;
	@JsonProperty("password")
	private String password;
	@JsonProperty("real_name")
	private String real_name;
	@JsonProperty("lastname")
	private String lastname;
	@JsonProperty("phone")
	private String phone;
}
