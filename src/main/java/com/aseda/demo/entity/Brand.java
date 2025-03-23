package com.aseda.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "brand")
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name", nullable = false, length = 255)
	private String name;
	
	@Column(name = "description", nullable = false, length = 500)
	private String description;
	
	@Column(name = "status", nullable = false, length = 30)
	private String status;
	
	@Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
	
	@Column(name = "userid", nullable = false)
	private Integer userId;

	public Brand(String name, String description, String status, LocalDateTime createdAt, Integer userId) {
		super();
		this.name = name;
		this.description = description;
		this.status = status;
		this.createdAt = createdAt;
		this.userId = userId;
	}

	public Brand() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
