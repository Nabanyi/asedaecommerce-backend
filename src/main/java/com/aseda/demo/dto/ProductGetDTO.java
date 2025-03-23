package com.aseda.demo.dto;

public class ProductGetDTO {
	    
	private Integer id;
	private String category;
	private String brand;
	private String name;
	private String description;
	private String specification;
	private Double price;
	private String sku;
	private String status;
	private String image;

    // Default constructor
    public ProductGetDTO() {
    }

    // Parameterised constructor
    public ProductGetDTO(Integer id, String category, String brand, String name, String description,
                        String specification, Double price, String sku, String status, String image) {
        this.id = id;
        this.category = category;
        this.brand = brand;
        this.name = name;
        this.description = description;
        this.specification = specification;
        this.price = price;
        this.sku = sku;
        this.status = status;
        this.image = image;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
