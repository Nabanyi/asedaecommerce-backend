package com.aseda.demo.requestobjs;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class CreateOrderRequest {
	
	@NotNull(message = "User address is required")
	private Integer address;
	
	@NotNull(message = "Product Id field is required")
	@JsonProperty("productids")
	private List<Integer> productIds;
	
	@NotNull(message = "Quantity field is required")
	private List<Double> quantities;
	
	public CreateOrderRequest() {
		super();
	}

	public Integer getAddress() {
		return address;
	}

	public void setAddress(Integer address) {
		this.address = address;
	}

	public List<Integer> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<Integer> productIds) {
		this.productIds = productIds;
	}

	public List<Double> getQuantities() {
		return quantities;
	}

	public void setQuantities(List<Double> quantities) {
		this.quantities = quantities;
	}
	
}
