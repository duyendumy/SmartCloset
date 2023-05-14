package com.example.smartclosetclient.model;


import java.util.Date;

public class Scheduler {

	private Long id;
	private Date selectedDate;
	private Outfit outfit;
	private Closet closet;

	public Scheduler() {
	}

	public Scheduler(Long id, Date selectedDate, Outfit outfit, Closet closet) {
		this.id = id;
		this.selectedDate = selectedDate;
		this.outfit = outfit;
		this.closet = closet;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	public Outfit getOutfit() {
		return outfit;
	}

	public void setOutfit(Outfit outfit) {
		this.outfit = outfit;
	}

	public Closet getCloset() {
		return closet;
	}

	public void setCloset(Closet closet) {
		this.closet = closet;
	}
}
