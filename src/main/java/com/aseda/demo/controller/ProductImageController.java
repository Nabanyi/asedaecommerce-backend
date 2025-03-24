package com.aseda.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aseda.demo.dto.ProductImageGetDTO;
import com.aseda.demo.service.ProductImageService;
import com.aseda.demo.utils.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/images")
@Tag(name = "Product Images", description = "APIs for Users Product Images")
public class ProductImageController {
	
	@Autowired
	private ProductImageService productImageService;
	
	@Operation(summary = "Get Images", description = "Get images for a product")
	@PreAuthorize("isAuthenticated()")
    @GetMapping("/get/{id}")
    public ApiResponse<List<ProductImageGetDTO>> getImages(@PathVariable(name="id") Integer productId) {
        return new ApiResponse<>(true, "Data retreived successfully", productImageService.getProductImages(productId));
    }
	
//	@Operation(summary = "2. Create Product Image", description = "Create images for a product")
//	@PreAuthorize("isAuthenticated()")
//    @PostMapping("/create")
//    public ApiResponse<String> getImages(List<ProductImageDTO> images) {
//		productImageService.createProductImage(images);
//        return new ApiResponse<>(true, "Data retreived successfully", null);
//    }
	
	@Operation(summary = "Delete Images", description = "Delete images for a product by image name")
	@PreAuthorize("isAuthenticated()")
    @GetMapping("/delete")
    public ApiResponse<String> deleteImages(@RequestParam("image") String imageName) {
		productImageService.deleteProductImage(imageName);
        return new ApiResponse<>(true, "Image deleted successfully", null);
    }
	
	
}
