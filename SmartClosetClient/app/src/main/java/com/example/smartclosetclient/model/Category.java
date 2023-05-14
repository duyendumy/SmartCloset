package com.example.smartclosetclient.model;

import java.util.Date;





public class Category {
	

	private Long id;

	private String imagePath;

	private String name;
	

	private Date createdDate;
	

	private Date updatedDate;
	
	

	private int display;


	
	public Category() {
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
