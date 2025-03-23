package com.aseda.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateCategoryDTO {
    
    @NotNull
    @Size(max = 255)
    private String name;
    
    @Size(max = 500)
    private String description;
    
    @Size(max = 30)
    private String status;

	public CreateCategoryDTO() {
	}

	public CreateCategoryDTO(@NotNull @Size(max = 255) String name,
			@Size(max = 500) String description, @Size(max = 30) String status) {
		super();
		this.name = name;
		this.description = description;
		this.status = status;
	}

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
