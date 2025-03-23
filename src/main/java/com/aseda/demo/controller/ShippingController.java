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

import com.aseda.demo.dto.ShippingCreateDTO;
import com.aseda.demo.dto.ShippingGetDTO;
import com.aseda.demo.dto.ShippingUpdateDTO;
import com.aseda.demo.service.ShippingService;
import com.aseda.demo.utils.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/shipping")
@Tag(name = "4. Shipping Adresses", description = "APIs for Users Shipping Address")
public class ShippingController {

	@Autowired
	private ShippingService shippingService;
	
	@Operation(summary = "1. Get all Shipping Adresses", description = "Get all user shipping addresses")
	@PreAuthorize("isAuthenticated()")
    @GetMapping("/get")
    public ApiResponse<List<ShippingGetDTO>> getShippings() {
        return new ApiResponse<>(true, "Data retreived successfully", shippingService.getAllShipping());
    }
	
	@Operation(summary = "2. Get a Shipping Adress", description = "Get user shipping address")
	@PreAuthorize("isAuthenticated()")
    @GetMapping("/single/{id}")
    public ApiResponse<ShippingGetDTO> getShipping(@PathVariable(name="id") Integer shippingId) {
        return new ApiResponse<>(true, "Data retreived successfully", shippingService.getShipping(shippingId));
    }
	
	@Operation(summary = "3. Create Shipping", description = "Create shipping address by user")
	@PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public ApiResponse<ShippingGetDTO> createCategories(@Valid @RequestBody ShippingCreateDTO createDTO) {
        return new ApiResponse<>(true, "Shipping address created successfully", shippingService.createShipping(createDTO));
    }
	
	@Operation(summary = "4. Update Shipping", description = "Update a shipping address by user")
	@PreAuthorize("isAuthenticated()")
    @PostMapping("/update/{id}")
    public ApiResponse<String> updateCategories(@PathVariable(name = "id") int shippingId, @Valid @RequestBody ShippingUpdateDTO updateDTO) {
		shippingService.updateShipping(shippingId, updateDTO);
		return new ApiResponse<>(true, "Shipping address updated successfully", null);
    }
	
	@Operation(summary = "5. Delete Shipping", description = "Deactivate a shipping address by Id")
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/deactivate/{id}")
	public ApiResponse<String> deleteShipping(@PathVariable(name = "id") int shippingId) {
		shippingService.deleteShipping(shippingId);
		return new ApiResponse<>(true, "Shipping address deactivated successfully", null);
	}
}
