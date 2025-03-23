package com.aseda.demo.dto;

import java.time.LocalDateTime;

public class BrandGetDTO {

	private Integer id;
	private Integer userId;
	private String name;
	private String description;
	private String status;
	private LocalDateTime createdAt;
	
	
	public BrandGetDTO() {
		super();
	}
	
	public BrandGetDTO(Integer id, Integer userId, String name, String description, String status, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.description = description;
		this.status = status;
		this.createdAt = createdAt;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
