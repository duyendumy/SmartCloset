package com.group.smartcloset.model;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "image_path", length = 16777215)
	private String imagePath;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_category", nullable = false)
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_subcategory", nullable = false)
	private SubCategory subCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_brand", nullable = false)
	private Brand brand;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_season", nullable = false)
	private Season season;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_closet", nullable = false)
	private Closet closet;

	@Column(name = "price", nullable = false, precision = 12, scale = 0)
	private Float price;

	@Column(name = "color", length = 100)
	private String color;

	@Column(name = "description", length = 16777215)
	private String description;

	@Column(name = "display")
	private int display;

	public Item() {
		super();
	}

	public Item(String imagePath, Category category, SubCategory subCategory, Brand brand, Season season, Closet closet,
			Float price, String color, String description, int display) {
		super();
		this.imagePath = imagePath;
		this.category = category;
		this.subCategory = subCategory;
		this.brand = brand;
		this.season = season;
		this.closet = closet;
		this.price = price;
		this.color = color;
		this.description = description;
		this.display = display;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	
	  public Closet getCloset() {
		  return closet; }
	  
	  public void setCloset(Closet closet) { 
		  this.closet = closet; }
	 

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDisplay() {
		return display;
	}

	public void setDisplay(int display) {
		this.display = display;
	}

}
