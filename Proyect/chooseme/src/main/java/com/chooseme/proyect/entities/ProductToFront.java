package com.chooseme.proyect.entities;


import lombok.Data;


@Data
public class ProductToFront {
	
	private String name;

	private String brand;

	private double price;

	private double score;

	private String description;

	private String photo;

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
 