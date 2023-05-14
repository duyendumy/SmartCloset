package com.group.smartcloset.model;

import jakarta.persistence.UniqueConstraint;

import java.util.Collection;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

import com.group.smartcloset.model.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "user",  uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "closet_id", nullable = false)
	private Long closetId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "active")
	private int active;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(
		            name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
				            name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;
	

	public User() {
	}
	

	public User(String email, Long closetId, String username, String password, Date createdDate, Date updatedDate,
			int active, Collection<Role> roles) {
		super();
		this.email = email;
		this.closetId = closetId;
		this.username = username;
		this.password = password;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.active = active;
		this.roles = roles;
	}


	public Long getClosetId() {
		return closetId;
	}


	public void setClosetId(Long closetId) {
		this.closetId = closetId;
	}


	public User(String email, String username, String password, Date createdDate, Date updatedDate, int active,
			Collection<Role> roles) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.active = active;
		this.roles = roles;
	}
	
	

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public User(String email,String username, String password) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
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


	public int getActive() {
		return active;
	}


	public void setActive(int active) {
		this.active = active;
	}


	public Collection<Role> getRoles() {
		return roles;
	}


	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [userId=" + id + ", email=" + email + ", username=" + username + ", password=" + password
				+ "]";
	}
	
	

}
