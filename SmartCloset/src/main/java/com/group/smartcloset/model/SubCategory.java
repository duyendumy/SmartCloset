package com.group.smartcloset.model;

import java.util.Date;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name =  "subcategory")
public class SubCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCategory", nullable = false)
	private Category category;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "createdDate")
	private Date createdDate;
	
	@Column(name = "updatedDate")
	private Date updatedDate;
	
	
	@Column(name = "display")
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
