package com.aseda.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aseda.demo.dto.ProductCreateDTO;
import com.aseda.demo.dto.ProductDetailsDTO;
import com.aseda.demo.dto.ProductGetDTO;
import com.aseda.demo.dto.ProductUpdateDTO;
import com.aseda.demo.requestobjs.ProductGetRequest;
import com.aseda.demo.service.ProductService;
import com.aseda.demo.utils.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
@Tag(name = "Product Items", description = "APIs for Users Product Items")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@Operation(summary = "Get all Items", description = "Get all Ads")
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/get")
    public ApiResponse<Page<ProductGetDTO>> getProducts(@RequestBody @Valid ProductGetRequest request) {
		
		Sort sort = request.getDirection().equalsIgnoreCase("desc") 
	            ? Sort.by(request.getSortBy()).descending() 
	            : Sort.by(request.getSortBy()).ascending();

	    Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);

	    Page<ProductGetDTO> products = productService.getAllProducts(request.getCategories(), request.getBrands(), pageable);

	    return new ApiResponse<>(true, "Data retrieved successfully", products);
    }
	
	@Operation(summary = "Get all User Items", description = "Get all user product items")
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/user-ads")
    public ApiResponse<List<ProductGetDTO>> getUserProducts() {
	    List<ProductGetDTO> products = productService.getUserAds();
	    return new ApiResponse<>(true, "Data retrieved successfully", products);
    }
	
	@Operation(summary = "Get a Product", description = "Get a single product")
	@PreAuthorize("isAuthenticated()")
    @GetMapping("/single/{id}")
    public ApiResponse<ProductDetailsDTO> getproduct(@PathVariable(name="id") Integer productId) {
        return new ApiResponse<>(true, "Data retreived successfully", productService.getProduct(productId));
    }
	
	@Operation(summary = "Create Product", description = "Create product by user")
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/create", consumes = "multipart/form-data")
    public ApiResponse<ProductGetDTO> createproduct(@Valid ProductCreateDTO createDTO) {
        return new ApiResponse<>(true, "Product created successfully", productService.createProduct(createDTO));
    }
	
	@Operation(summary = "Update Product", description = "Update a Product by user")
	@PreAuthorize("isAuthenticated()")
    @PostMapping(value="/update/{id}", consumes="multipart/form-data")
    public ApiResponse<ProductGetDTO> updateProduct(@PathVariable(name = "id") int productId, @Valid ProductUpdateDTO updateDTO) {
		return new ApiResponse<>(true, "Product details updated successfully", productService.updateProduct(productId, updateDTO));
    }
	
	@Operation(summary = "Delete Product", description = "Deactivate a Product Item by Id")
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/deactivate/{id}")
	public ApiResponse<String> deleteProduct(@PathVariable(name = "id") int productId) {
		productService.deactivateProduct(productId);
		return new ApiResponse<>(true, "Product deactivated successfully", null);
	}
	
	@Operation(summary = "Similar Product", description = "Get similar products for a brand and category")
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/similar-products/{brandId}/{categoryId}")
	public ApiResponse<List<ProductGetDTO>> getSimilarProducts(@PathVariable(name = "brandId") int brand, @PathVariable(name = "categoryId") int category) {
		return new ApiResponse<>(true, "Data retrieved successfully", productService.getSimilarProducts(brand, category));
	}
}
