package com.aseda.demo.dto;

public class ShippingCreateDTO {
    
    private String country;
    private String region;
    private String location;
    private String address;
    private String street;
    private String landmark;

    // Default constructor
    public ShippingCreateDTO() {
    }

    // Parameterised constructor
    public ShippingCreateDTO(String country, String region, String location, 
                            String address, String street, String landmark) {
        this.country = country;
        this.region = region;
        this.location = location;
        this.address = address;
        this.street = street;
        this.landmark = landmark;
    }

    // Getters and Setters
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
