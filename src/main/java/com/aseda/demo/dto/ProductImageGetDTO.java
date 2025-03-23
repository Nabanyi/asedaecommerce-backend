package com.aseda.demo.dto;

public class ProductImageGetDTO {
    
    private Integer id;
    private Integer productId;
    private String image;

    // Default constructor
    public ProductImageGetDTO() {
    }

    // Parameterized constructor
    public ProductImageGetDTO(Integer id, Integer productId, String image) {
        this.id = id;
        this.productId = productId;
        this.image = image;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
