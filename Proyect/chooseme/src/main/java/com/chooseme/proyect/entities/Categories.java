package com.chooseme.proyect.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "types")
@Table(name = "types")
public class Categories {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="type_id")
	private int type_id;
	
	@Column(name = "type")
	private String type;
	
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		
	}
}
