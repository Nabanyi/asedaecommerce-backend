package com.aseda.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "userid", nullable = false)
    private Integer userId;
    
    @Column(name = "category", nullable = false)
    private Integer category;
    
    @Column(name = "brand", nullable = false)
    private Integer brand;
    
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    
    @Column(name = "specification", nullable = false, columnDefinition = "MEDIUMTEXT")
    private String specification;
    
    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "price", nullable = false)
    private Double price;
    
    @Column(name = "sku", nullable = false, length = 30)
    private String sku;
    
    @Column(name = "status", nullable = false, length = 30)
    private String status;

    // Default constructor
    public Product() {
    }

    // Parameterised constructor
    public Product(Integer userId, Integer category, Integer brand, String name, String description,
                  String specification, Double price, String sku, String status) {
        this.userId = userId;
        this.category = category;
        this.brand = brand;
        this.name = name;
        this.description = description;
        this.specification = specification;
        this.price = price;
        this.sku = sku;
        this.status = status;
    }

    // Getters and Setters
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

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getBrand() {
        return brand;
    }

    public void setBrand(Integer brand) {
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
    
    
}
