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

import com.aseda.demo.dto.ReviewCreateDTO;
import com.aseda.demo.entity.Review;
import com.aseda.demo.service.ReviewService;
import com.aseda.demo.utils.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/reviews")
@Tag(name = "7. Reviews", description = "APIs for Reviews")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@Operation(summary = "1. Get Reviews", description = "Get all reviews for a product")
	@PreAuthorize("isAuthenticated()")
    @GetMapping("/get/{productID}")
	public ApiResponse<List<Review>> getProductReviews(@PathVariable(name="productID") Integer productId) {
		return new ApiResponse<>(true, "Data retreived successfully", reviewService.getAllReviews(productId));
	}
	
	@Operation(summary = "2. Create Reviews", description = "Create reviews for a product")
	@PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
	public ApiResponse<Review> createReviews(@Valid @RequestBody ReviewCreateDTO createDTO) {
		return new ApiResponse<>(true, "Data saved successfully", reviewService.createReview(createDTO));
	}
	
	
}
