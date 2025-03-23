package com.aseda.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "shipping")
public class Shipping {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "userid")
    private Integer userId;
    
    @Column(name = "country", length = 100)
    private String country;
    
    @Column(name = "region", length = 100)
    private String region;
    
    @Column(name = "location", length = 255)
    private String location;
    
    @Column(name = "address", length = 255)
    private String address;
    
    @Column(name = "street", length = 255)
    private String street;
    
    @Column(name = "landmark", length = 255)
    private String landmark;

    // Default constructor
    public Shipping() {
    }

    // Parameterised constructor
    public Shipping(Integer userId, String country, String region, String location, 
                   String address, String street, String landmark) {
        this.userId = userId;
        this.country = country;
        this.region = region;
        this.location = location;
        this.address = address;
        this.street = street;
        this.landmark = landmark;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }
}
