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
	
		
		
}
 