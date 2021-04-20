package com.chooseme.proyect.dto;

import java.sql.Date;

public class ProductsFilters {
	
	private Date create_at;
	private String name;
	private double stars_punctuation;
	private int popularity;
	private String category;
	
	public String getName() {
		return name;
	}
	public void setNasstemp(String name) {
		this.name = name;
	}
	
	public Date getCreate_at() {
		return create_at;
	}
	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}
	
	public double getStars_punctuation() {
		return stars_punctuation;
	}
	public void setStars_punctuation(double stars_punctuation) {
		this.stars_punctuation = stars_punctuation;
	}
	
	public int getPopularity() {
		return popularity;
	}
	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	

}
