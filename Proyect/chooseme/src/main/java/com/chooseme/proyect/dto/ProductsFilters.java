package com.chooseme.proyect.dto;


public class ProductsFilters {
	
	private String create_at;
	private String name;
	private String stars_puntuation;
	private int popularity;
	private String category;
	private String nameorcat;
	
	
	public String getNameorCat() {
		return nameorcat;
	}
	public void setNameorCat(String nameorcat) {
		this.nameorcat = nameorcat;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCreate_at() {
		return create_at;
	}
	public void setCreate_at(String create_at) {
		this.create_at = create_at;
	}
	
	public String getStars_puntuation() {
		return stars_puntuation;
	}
	public void setStars_puntuation(String stars_puntuation) {
		this.stars_puntuation = stars_puntuation;
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
