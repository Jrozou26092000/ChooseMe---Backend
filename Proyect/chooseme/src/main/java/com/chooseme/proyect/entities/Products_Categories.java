package com.chooseme.proyect.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "product_types")
@Table(name = "product_types")
public class Products_Categories {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_types_id")
	private int id;
	
	@Column(name="product_id")
	private int product_id;
	
	@Column(name = "type_id")
	private int type_id;
	
	public int getProduct_types_id() {
		return id;
	}
	public void setProduct_types_id(int product_id) {
		this.id = product_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
}
