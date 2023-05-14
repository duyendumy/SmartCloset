package com.group.smartcloset.model;
import java.util.Date;
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
@Table(name =  "scheduler")
public class Scheduler {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "selected_date")
	private Date selectedDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "outfit_id", nullable = false)
	private Outfit outfit;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_closet", nullable = false)
	private Closet closet;
	
	

	public Scheduler(Date selectedDate, Outfit outfit, Closet closet) {
		super();
		this.selectedDate = selectedDate;
		this.outfit = outfit;
		this.closet = closet;
	}


	public Scheduler() {
		super();
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
