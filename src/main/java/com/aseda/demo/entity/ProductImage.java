package com.aseda.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_images")
public class ProductImage {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "productid", nullable = false)
    private Integer productId;
    
    @Column(name = "image", nullable = false, length = 500)
    private String image;

    // Default constructor
    public ProductImage() {
    }

    // Parameterised constructor
    public ProductImage(Integer productId, String image) {
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
