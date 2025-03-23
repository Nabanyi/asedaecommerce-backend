package com.aseda.demo.dto;

public class ProductImageDTO {
    
    private Integer productId;
    private String image;

    // Default constructor
    public ProductImageDTO() {
    }

    // Parameterised constructor
    public ProductImageDTO(Integer productId, String image) {
        this.productId = productId;
        this.image = image;
    }

    // Getters and Setters
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
