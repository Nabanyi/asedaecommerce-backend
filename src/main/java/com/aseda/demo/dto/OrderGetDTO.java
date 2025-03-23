package com.aseda.demo.dto;

import java.time.LocalDate;
import java.util.List;


public class OrderGetDTO {

	private Integer orderid;
	private LocalDate transDate;
	private ShippingGetDTO address;
	private Double totalAmount;
	private String customer;
	private String status;
	private List<OrderItemsDTO> items;
	
	public OrderGetDTO() {
		super();
	}

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public LocalDate getTransDate() {
		return transDate;
	}

	public void setTransDate(LocalDate transDate) {
		this.transDate = transDate;
	}

	public ShippingGetDTO getAddress() {
		return address;
	}

	public void setAddress(ShippingGetDTO address) {
		this.address = address;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<OrderItemsDTO> getItems() {
		return items;
	}

	public void setItems(List<OrderItemsDTO> items) {
		this.items = items;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
