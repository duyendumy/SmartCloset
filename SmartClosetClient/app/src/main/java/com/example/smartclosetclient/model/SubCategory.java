package com.example.smartclosetclient.model;

import java.util.Date;




public class SubCategory {

	private Long id;
	

	private Category category;
	

	private String name;
	

	private Date createdDate;
	

	private Date updatedDate;
	
	

	private int display;


	public SubCategory() {

	}


	public SubCategory(Category category, String name, Date createdDate, Date updatedDate, int display) {
		super();
		this.category = category;
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


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
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
	
	

}
