package com.chooseme.proyect.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "scores")
@Table(name = "scores")
public class Scores {
	
	@Id
	@Column(name = "score_id")
	private int score_id;
	@Column(name = "score")
	private int score;
	@Column(name = "create_at")
	private Timestamp create_at;
	@Column(name = "reviewer_id")
	private int reviewer_id;
	@Column(name = "product_id")
	private int product_id;
	@Transient
	private double scoreAVG;
}
