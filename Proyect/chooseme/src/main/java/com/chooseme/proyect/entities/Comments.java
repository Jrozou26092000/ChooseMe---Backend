package com.chooseme.proyect.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "comments")
@Table(name = "comments")
public class Comments {

	@Id
	@Column(name = "comment_id")
	private int comment_id;
	@Column(name = "comment")
	private String comment;
	@Column(name = "create_at")
	private Timestamp create_at;
	@Column(name = "reviewer_id")
	private int reviewer_id;
	@Column(name = "product_id")
	private int product_id;
}
