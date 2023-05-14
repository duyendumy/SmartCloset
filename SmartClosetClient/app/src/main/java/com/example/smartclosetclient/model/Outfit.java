package com.example.smartclosetclient.model;


import java.util.Collection;

public class Outfit {

	private Long id;

	private Closet closet;

	private Collection<Item> items;



	private int display;

	public Outfit(Long id, Closet closet, Collection<Item> items, int display) {
		this.id = id;
		this.closet = closet;
		this.items = items;
		this.display = display;
	}

	public Outfit() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Closet getCloset() {
		return closet;
	}

	public void setCloset(Closet closet) {
		this.closet = closet;
	}

	public Collection<Item> getItems() {
		return items;
	}

	public void setItems(Collection<Item> items) {
		this.items = items;
	}

	public int getDisplay() {
		return display;
	}

	public void setDisplay(int display) {
		this.display = display;
	}
}
