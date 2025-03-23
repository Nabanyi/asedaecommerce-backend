package com.aseda.demo.dto;

import java.util.List;

public class ProductDetailsDTO {

	private Integer id;
	private Integer categoryId;
	private Integer brandId;
	private String category;
	private String brand;
	private String name;
	private String description;
	private String specification;
	private Double price;
	private String sku;
	private String status;
	private List<String> images;
	
	
	public ProductDetailsDTO() {
	}


	public ProductDetailsDTO(Integer id, Integer categoryId, Integer brandId, String category, String brand,
			String name, String description, String specification, Double price, String sku, String status,
			List<String> images) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.brandId = brandId;
		this.category = category;
		this.brand = brand;
		this.name = name;
		this.description = description;
		this.specification = specification;
		this.price = price;
		this.sku = sku;
		this.status = status;
		this.images = images;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}


	public Integer getBrandId() {
		return brandId;
	}


	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSpecification() {
		return specification;
	}


	public void setSpecification(String specification) {
		this.specification = specification;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public String getSku() {
		return sku;
	}


	public void setSku(String sku) {
		this.sku = sku;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public List<String> getImages() {
		return images;
	}


	public void setImages(List<String> images) {
		this.images = images;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
