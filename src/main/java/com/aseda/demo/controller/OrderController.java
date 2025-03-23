package com.aseda.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aseda.demo.dto.OrderGetDTO;
import com.aseda.demo.requestobjs.CreateOrderRequest;
import com.aseda.demo.service.OrdersService;
import com.aseda.demo.utils.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
@Tag(name = "8. Orders", description = "APIs for Orders")
public class OrderController {
	@Autowired
	private OrdersService ordersService;
	
	@Operation(summary = "1. Get Orders made", description = "Get all orders made")
	@PreAuthorize("isAuthenticated()")
    @GetMapping("/orders-placed")
    public ApiResponse<List<OrderGetDTO>> getOrdersPlaced() {
        return new ApiResponse<>(true, "Data retreived successfully", ordersService.getOrdersPlaced());
    }
	
	@Operation(summary = "2. Get Orders received", description = "Get all orders received")
	@PreAuthorize("isAuthenticated()")
    @GetMapping("/orders-received")
    public ApiResponse<List<OrderGetDTO>> getOrdersReceived() {
        return new ApiResponse<>(true, "Data retreived successfully", ordersService.getOrdersReceived());
    }
	
	@Operation(summary = "3. Create Orders", description = "Create orders")
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create-order")
	public ApiResponse<String> createOrder(@Valid @RequestBody CreateOrderRequest request) {
		ordersService.createOrder(request);
		return new ApiResponse<>(true, "Order placed successfully", null);
	}
}
