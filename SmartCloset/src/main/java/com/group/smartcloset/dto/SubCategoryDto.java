package com.group.smartcloset.dto;

public class SubCategoryDto {
	
	private String name;

	private int display;
	
	private Long categoryId;

	
	public SubCategoryDto() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDisplay() {
		return display;
	}

	public void setDisplay(int display) {
		this.display = display;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}


	

}
