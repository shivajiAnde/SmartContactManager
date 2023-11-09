package com.smart.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cId;
	
	private String name;
	
	private String nickName;
	
	private String email;
	
	private String imageUrl;
	
	private String phone;
	
	@Column(length = 5000)
	private String description;
	
	private String work;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
}
