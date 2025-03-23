package com.aseda.demo.requestobjs;

import java.util.List;

public class ProductGetRequest {

	private int page = 0;
    private int size = 5;
    private String sortBy = "id";
    private String direction = "asc";
    private List<Integer> categories;
    private List<Integer> brands;
    
	public ProductGetRequest() {
		super();
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public List<Integer> getCategories() {
		return categories;
	}
	public void setCategories(List<Integer> categories) {
		this.categories = categories;
	}
	public List<Integer> getBrands() {
		return brands;
	}
	public void setBrands(List<Integer> brands) {
		this.brands = brands;
	}
    
    
}
