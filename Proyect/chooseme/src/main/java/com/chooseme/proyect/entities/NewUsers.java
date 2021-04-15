package com.chooseme.proyect.entities;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;


public class NewUsers extends Users {


	private String passtemp;

	
	public String getPasswordTemp() {
		return passtemp;
	}
	public void setPasswordTemp(String passtemp) {
		this.passtemp = passtemp;
	}
	
	
	
}
