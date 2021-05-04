package com.chooseme.proyect.entities;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;	
import javax.persistence.Entity;	
import javax.persistence.GeneratedValue;	
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

/*
 * Estructura de la entidad usuario con los datos en la base de datos
 * cada dato refiere directamente a su parte en la db
 * al final se encuentran las funciones para la generación automática de create_at y la actualización automatica
 * de modified_at
 */
@Entity	
@Table(name = "Users")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
	private int user_id;
	
	@Column(name="user_photo")
	private Blob user_photo;
	@Column(name= "user_photo_google")
	private String photo_url;
	@Column(name="user_name")
	private String user_name;
	@Column(name="email")
	private String email;
	@Column(name="password")
	private String password;
	@Column(name="active")
	private int active;
	@Column(name="name")
	private String real_name;
	@Column(name="lastname")
	private String lastname;
	@Column(name="phone")
	private String phone;
	@Column(name="points")
	private int points;
	@Column(name="google_account")
	private String google_account;
	@Column(name="created_at")
	private Timestamp created_at;
	@Column(name = "modified_at")
	private Timestamp modified_at;
	@Transient
	private String passtemp;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.PERSIST)
	private List<Comments> comment = new ArrayList<Comments>();
	
	
	public String getPasstemp() {
		return passtemp;
	}
	public void setPasstemp(String passtemp) {
		this.passtemp = passtemp;
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
	
	public String getUser_photo_url() {
		return photo_url;
	}
	public void setUser_photo_url(String photo_url) {
		this.photo_url = photo_url;
	}
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public String getName() {
		return real_name;
	}
	public void setName(String real_name) {
		this.real_name = real_name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	
	public Timestamp getCreated_at(){
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	
	public Timestamp getModified_at() {
		return modified_at;
	}
	public void setModified_at(Timestamp modified_at) {
		this.modified_at = modified_at;
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
