package com.aseda.demo.dto;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;


public class ImageDTO {
	private String purpose;
	private String name;
	private List<MultipartFile> images;
	public ImageDTO() {
		super();
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<MultipartFile> getImages() {
		return images;
	}
	public void setImages(List<MultipartFile> images) {
		this.images = images;
	}
	
	

}
