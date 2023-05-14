package com.group.smartcloset.model;

import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name =  "category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "image_path", length = 16777215)
	private String imagePath;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	private Date updatedDate;
	
	
	@Column(name = "display")
	private int display;


	
	public Category() {
	}


	public Category(String name, Date createdDate, Date updatedDate, int display) {
		super();
		this.name = name;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.display = display;
	}


	public Category(String imagePath, String name, Date createdDate, Date updatedDate, int display) {
		super();
		this.imagePath = imagePath;
		this.name = name;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.display = display;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public Date getUpdatedDate() {
		return updatedDate;
	}


	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}


	public int getDisplay() {
		return display;
	}


	public void setDisplay(int display) {
		this.display = display;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
	
}
