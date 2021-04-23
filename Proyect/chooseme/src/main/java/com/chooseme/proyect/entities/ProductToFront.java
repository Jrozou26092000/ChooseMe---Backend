package com.chooseme.proyect.dto;

import javax.persistence.Column;

public class ProductToFront {
	
	@Column(name = "name")
	private String name;
	@Column(name = "brand")
	private String brand;
	@Column(name = "price")
	private double price;
	@Column(name = "score")
	private double score;
	@Column(name = "description")
	private String description;
	@Column(name = "photo")
	private String photo;
	@Column(name = "comments")
	private String comments;

	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
	public String setDescription() {
		return description;
	}
	public void getDescription(String description) {
		this.description = description;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getComments(){
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
		
		
}
 