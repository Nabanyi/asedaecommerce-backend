package com.aseda.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public class ProductCreateDTO {
    @NotNull
    private Integer category;
    @NotNull
    private Integer brand;
    @NotNull
    @Size(max = 255)
    private String name;
    @NotNull
    private String specification;
    private String description;
    @NotNull
    private Double price;
    @NotNull
    @Size(max = 30)
    private String sku;
    @NotNull
    @Size(max = 30)
    private String status;
    @NotNull
    private List<MultipartFile> images;
    
	public ProductCreateDTO() {
	}

	public ProductCreateDTO(@NotNull Integer category, @NotNull Integer brand, @NotNull @Size(max = 255) String name,
			@NotNull String specification, String description, @NotNull Double price,
			@NotNull @Size(max = 30) String sku, @NotNull @Size(max = 30) String status,
			@NotNull List<MultipartFile> images) {
		super();
		this.category = category;
		this.brand = brand;
		this.name = name;
		this.specification = specification;
		this.description = description;
		this.price = price;
		this.sku = sku;
		this.status = status;
		this.images = images;
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

	public List<MultipartFile> getImages() {
		return images;
	}

	public void setImages(List<MultipartFile> images) {
		this.images = images;
	}
	
	
    
    
}