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

import com.aseda.demo.dto.CreateCategoryDTO;
import com.aseda.demo.dto.GetCategoryDTO;
import com.aseda.demo.service.CategoryService;
import com.aseda.demo.utils.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
@Tag(name = "Product Category", description = "APIs for Product Categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	// Get All Categories
	@Operation(summary = "Get All Category", description = "Get all categories")
	@PreAuthorize("isAuthenticated()")
    @GetMapping("/get")
    public ApiResponse<List<GetCategoryDTO>> getCategories() {
        return new ApiResponse<>(true, "Data retreived successfully", categoryService.getUserCategories());
    }
	
	@Operation(summary = "Get a Category", description = "Get a single category")
	@PreAuthorize("isAuthenticated()")
    @GetMapping("/single/{id}")
    public ApiResponse<GetCategoryDTO> getCategory(@PathVariable(name = "id") int categoryId) {
        return new ApiResponse<>(true, "Category retrieved successfully", categoryService.getUserCategory(categoryId));
    }
	
	@Operation(summary = "Create Category", description = "Create a category")
	@PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public ApiResponse<GetCategoryDTO> createCategories(@Valid @RequestBody CreateCategoryDTO categoryDTO) {
        return new ApiResponse<>(true, "Category created successfully", categoryService.createCategory(categoryDTO));
    }
	
	@Operation(summary = "Update Category", description = "Update a category")
	@PreAuthorize("isAuthenticated()")
    @PostMapping("/update/{id}")
    public ApiResponse<GetCategoryDTO> updateCategories(@PathVariable(name = "id") int categoryId, @Valid @RequestBody CreateCategoryDTO categoryDTO) {
        return new ApiResponse<>(true, "Category updated successfully", categoryService.updateCategory(categoryId, categoryDTO));
    }
	
	@Operation(summary = "Deactivate Category", description = "Deactivate a category by Id")
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/deactivate/{id}")
	public ApiResponse<GetCategoryDTO> deleteCategory(@PathVariable(name = "id") int categoryId) {
		return new ApiResponse<>(true, "Category deactivated successfully", categoryService.deleteCategory(categoryId));
	}
}
