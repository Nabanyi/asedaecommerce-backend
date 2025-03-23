package com.aseda.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aseda.demo.dto.ProductImageDTO;
import com.aseda.demo.dto.ProductImageGetDTO;
import com.aseda.demo.entity.ProductImage;
import com.aseda.demo.exception.ResourceNotFoundException;
import com.aseda.demo.repository.ProductImageRepository;

@Service
public class ProductImageService {
	
	@Autowired
	private ProductImageRepository productImageRepository;
	@Autowired
    private FileStorageService fileStorageService;
	
	public List<ProductImageGetDTO> getProductImages(Integer producId){
		List<ProductImage> images = productImageRepository.findAllByProductId(producId);
		return images.stream()
	            .map(image -> {
	                ProductImageGetDTO dto = new ProductImageGetDTO();
	                BeanUtils.copyProperties(image, dto);
	                return dto;
	            })
	            .collect(Collectors.toList());
	}
	
	public void createProductImage(List<ProductImageDTO> createDTOs) {
		if (createDTOs == null || createDTOs.isEmpty()) {
            throw new IllegalArgumentException("Product image list cannot be null or empty");
        }
		
		List<ProductImage> productImages = createDTOs.stream().map(item -> {
			ProductImage pImage = new ProductImage();
			BeanUtils.copyProperties(item, pImage);
			return pImage;
		}).collect(Collectors.toList());
		productImageRepository.saveAll(productImages);
	}
	
	public void deleteProductImage(String imageName) {
		ProductImage image = productImageRepository.findByImage(imageName);
		productImageRepository.delete(image);
		FileStorageService.deleteImage(image.getImage());
	}
}
