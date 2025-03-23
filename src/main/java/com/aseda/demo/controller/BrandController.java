package com.aseda.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aseda.demo.dto.BrandUpdateDTO;
import com.aseda.demo.dto.BrandCreateDTO;
import com.aseda.demo.dto.BrandGetDTO;
import com.aseda.demo.service.BrandService;
import com.aseda.demo.utils.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/brand")
@Tag(name = "3. Product Brand", description = "APIs for Product Brands")
public class BrandController {

	@Autowired
	private BrandService brandService;
	
	@Operation(summary = "1. Get All Brands", description = "Get all brands by user")
	@PreAuthorize("isAuthenticated()")
    @GetMapping("/get")
    public ApiResponse<List<BrandGetDTO>> getBrands() {
        return new ApiResponse<>(true, "Data retreived successfully", brandService.getUserBrands());
    }
	
	@Operation(summary = "2. Get a Brand", description = "Get a single brand by user")
	@PreAuthorize("isAuthenticated()")
    @GetMapping("/single/{id}")
    public ApiResponse<BrandGetDTO> getBrand(@PathVariable(name = "id") int productId) {
        return new ApiResponse<>(true, "Brand retrieved successfully", brandService.getUserBrand(productId));
    }
	
	@Operation(summary = "3. Create Brand", description = "Create a brand by user")
	@PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public ApiResponse<BrandGetDTO> createBrands(@Valid @RequestBody BrandCreateDTO brandDTO) {
        return new ApiResponse<>(true, "Brand created successfully", brandService.createBrand(brandDTO));
    }
	
	@Operation(summary = "4. Update Brand", description = "Update a brand by user")
	@PreAuthorize("isAuthenticated()")
    @PostMapping("/update/{id}")
    public ApiResponse<BrandGetDTO> updateBrand(@PathVariable(name = "id") int brandId, @Valid @RequestBody BrandUpdateDTO brandDTO) {
        return new ApiResponse<>(true, "Brand updated successfully", brandService.updateBrand(brandId, brandDTO));
    }
	
	@Operation(summary = "5. Delete Category", description = "Deactivate a category by Id")
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/deactivate/{id}")
	public ApiResponse<BrandGetDTO> deleteCategory(@PathVariable(name = "id") int brandId) {
		return new ApiResponse<>(true, "Brand deactivated successfully", brandService.deleteBrand(brandId));
	}
}
