package com.aseda.demo.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BrandUpdateDTO {
	
	@NotNull
    @Size(max = 255)
    private String name;
	
	@NotNull
    @Size(max = 255)
    private String description;
	
	@NotNull
    @Size(max = 30)
    private String status;

    // Default constructor
    public BrandUpdateDTO() {
    }

    // Parameterised constructor
    public BrandUpdateDTO(String name, String description, String status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    // Getters and Setters
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

