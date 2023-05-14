package com.group.smartcloset.model;

import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name =  "outfit")
public class Outfit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_closet", nullable = false)
	private Closet closet;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "outfits_items",
			joinColumns = @JoinColumn(
		            name = "outfit_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
				            name = "item_id", referencedColumnName = "id"))
	private Collection<Item> items;
	
	@Column(name = "display")
	private int display;

	public Outfit() {
		super();
	}

	public Outfit(Closet closet, Collection<Item> items, int display) {
		super();
		this.closet = closet;
		this.items = items;
		this.display = display;
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
