package com.example.smartclosetclient.model;


public class Season {
	

	private Long id;
	

	private String name;

	public Season(String name) {
		super();
		this.name = name;
	}

	public Season() {
		super();
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
	

}
