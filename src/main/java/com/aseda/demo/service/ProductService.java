package com.aseda.demo.service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;

import com.aseda.demo.repository.BrandRepository;
import com.aseda.demo.repository.CategoryRepository;
import com.aseda.demo.repository.ProductImageRepository;
import com.aseda.demo.repository.ProductRepository;
import com.aseda.demo.dto.ProductCreateDTO;
import com.aseda.demo.dto.ProductDetailsDTO;
import com.aseda.demo.dto.ProductGetDTO;
import com.aseda.demo.dto.ProductUpdateDTO;
import com.aseda.demo.entity.Brand;
import com.aseda.demo.entity.Category;
import com.aseda.demo.entity.CustomUserDetails;
import com.aseda.demo.entity.Product;
import com.aseda.demo.entity.ProductImage;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private ProductImageRepository productImageRepository;
	@Autowired
    private FileStorageService fileStorageService;
	
	public Page<ProductGetDTO> getAllProducts(List<Integer> categoryList, List<Integer> brandList, Pageable pageable) {
		Page<Product> productsPage;
		
		if ((categoryList == null || categoryList.isEmpty()) && (brandList == null || brandList.isEmpty())) {
			productsPage = repository.findByStatus("Active", pageable);
		}else if (categoryList != null && !categoryList.isEmpty() && (brandList == null || brandList.isEmpty())) {
			productsPage = repository.findByCategory(categoryList, pageable);
		} else {
			productsPage = repository.findByCategoryAndBrand(categoryList, brandList, pageable);
		}
		
		List<Category> categories = categoryRepository.findAllByUserId(getUserId());
		Map<Integer, String> categoryMap = categories.stream().collect(Collectors.toMap(Category::getId, Category::getName));
		
		List<Brand> brands = brandRepository.findAllByUserId(getUserId());
		Map<Integer, String> brandMap = brands.stream().collect(Collectors.toMap(Brand::getId, Brand::getName));
		
		List<ProductImage> images = productImageRepository.findSingleRowForImages();
		Map<Integer, String> imageMap = images.stream().collect(Collectors.toMap(ProductImage::getProductId, ProductImage::getImage));
		
		List<ProductGetDTO> resultList = productsPage.stream().map(p -> {
			String categoryName = categoryMap.get(p.getCategory());
			String brandName = brandMap.get(p.getBrand());
			String image = imageMap.get(p.getId());
			ProductGetDTO pDto = new ProductGetDTO();
			BeanUtils.copyProperties(p, pDto);
			pDto.setBrand(brandName);
			pDto.setCategory(categoryName);
			pDto.setImage(image);
			return pDto;
			
		}).collect(Collectors.toList());
		
		return new PageImpl<>(resultList, pageable, productsPage.getTotalElements());
	}
	public List<ProductGetDTO> getUserAds() {
		
		List<Product> productsPage = repository.findByUserIdAndStatus(getUserId(), "Active");
		
		List<Category> categories = categoryRepository.findAllByUserId(getUserId());
		Map<Integer, String> categoryMap = categories.stream().collect(Collectors.toMap(Category::getId, Category::getName));
		
		List<Brand> brands = brandRepository.findAllByUserId(getUserId());
		Map<Integer, String> brandMap = brands.stream().collect(Collectors.toMap(Brand::getId, Brand::getName));
		
		List<ProductImage> images = productImageRepository.findSingleRowForImages();
		Map<Integer, String> imageMap = images.stream().collect(Collectors.toMap(ProductImage::getProductId, ProductImage::getImage));
		
		List<ProductGetDTO> resultList = productsPage.stream().map(p -> {
			String categoryName = categoryMap.get(p.getCategory());
			String brandName = brandMap.get(p.getBrand());
			String image = imageMap.get(p.getId());
			ProductGetDTO pDto = new ProductGetDTO();
			BeanUtils.copyProperties(p, pDto);
			pDto.setBrand(brandName);
			pDto.setCategory(categoryName);
			pDto.setImage(image);
			return pDto;
			
		}).collect(Collectors.toList());
		
		return resultList;
	}
	
	public ProductDetailsDTO getProduct(Integer productId) {
		Product product = repository.findProductById(productId, getUserId());
		
		List<Category> categories = categoryRepository.findAllByUserId(getUserId());
		Map<Integer, String> categoryMap = categories.stream().collect(Collectors.toMap(Category::getId, Category::getName));
		
		List<Brand> brands = brandRepository.findAllByUserId(getUserId());
		Map<Integer, String> brandMap = brands.stream().collect(Collectors.toMap(Brand::getId, Brand::getName));
		
		ProductDetailsDTO productDetailsDTO = new ProductDetailsDTO();
		BeanUtils.copyProperties(product, productDetailsDTO);
		productDetailsDTO.setCategory(categoryMap.get(product.getCategory()));
		productDetailsDTO.setBrand(brandMap.get(product.getBrand()));
		productDetailsDTO.setBrandId(product.getBrand());	
		productDetailsDTO.setCategoryId(product.getCategory());	
		List<ProductImage> pImages = productImageRepository.findAllByProductId(productId);
		List<String> imageStrings = new ArrayList<>();
		for (ProductImage image : pImages) {
		    imageStrings.add(image.getImage());
		}
		productDetailsDTO.setImages(imageStrings);
		
		return productDetailsDTO;
	}
	
	public ProductGetDTO createProduct(ProductCreateDTO createDTO) {
		Product product = new Product();
		BeanUtils.copyProperties(createDTO, product);
		product.setUserId(getUserId());
		Product savedProduct = repository.save(product);
		
		ProductGetDTO returnDto = new ProductGetDTO();
		BeanUtils.copyProperties(savedProduct, returnDto);
		
        try {
            List<String> imageNames = fileStorageService.storeFiles(createDTO.getImages());
            List<ProductImage> productImages = imageNames.stream()
                .map(name -> {
                    ProductImage pImage = new ProductImage();
                    pImage.setProductId(savedProduct.getId());
                    pImage.setImage(name);
                    returnDto.setImage(name);
                    return pImage;
                })
                .collect(Collectors.toList());
            productImageRepository.saveAll(productImages);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store product images", e);
        }
        
        return returnDto;
	}
	
	public ProductGetDTO updateProduct(Integer pid, ProductUpdateDTO updateDTO) {
		Product product = new Product();
		BeanUtils.copyProperties(updateDTO, product);
		product.setId(pid);
		product.setUserId(getUserId());
		Product savedProduct = repository.save(product);
		
		ProductGetDTO returnDto = new ProductGetDTO();
		BeanUtils.copyProperties(savedProduct, returnDto);
		
		if(updateDTO.getImages() != null) {
			try {
	            List<String> imageNames = fileStorageService.storeFiles(updateDTO.getImages());
	            List<ProductImage> productImages = imageNames.stream()
	                .map(name -> {
	                    ProductImage pImage = new ProductImage();
	                    pImage.setProductId(savedProduct.getId());
	                    pImage.setImage(name);
	                    return pImage;
	                })
	                .collect(Collectors.toList());
	            productImageRepository.saveAll(productImages);
	        } catch (IOException e) {
	            throw new RuntimeException("Failed to store product images", e);
	        }
		}
		
		Optional<ProductImage> image = productImageRepository.findFirstByProductId(pid);
		returnDto.setImage(image.map(ProductImage::getImage).orElse(null));
		
		return returnDto;
	}
	
	public void deactivateProduct(Integer pid) {
		Product product = repository.findProductById(pid, getUserId());
		product.setStatus("Inactive");
		repository.save(product);
	}
	
	public List<ProductGetDTO> getSimilarProducts(Integer brand, Integer category) {
		List<Product> productsPage = repository.findByBrandOrCategory(brand, category);
		
		List<Category> categories = categoryRepository.findAll();
		Map<Integer, String> categoryMap = categories.stream().collect(Collectors.toMap(Category::getId, Category::getName));
		
		List<Brand> brands = brandRepository.findAll();
		Map<Integer, String> brandMap = brands.stream().collect(Collectors.toMap(Brand::getId, Brand::getName));
		
		List<ProductImage> images = productImageRepository.findSingleRowForImages();
		Map<Integer, String> imageMap = images.stream().collect(Collectors.toMap(ProductImage::getProductId, ProductImage::getImage));
		
		List<ProductGetDTO> resultList = productsPage.stream().map(p -> {
			String categoryName = categoryMap.get(p.getCategory());
			String brandName = brandMap.get(p.getBrand());
			String image = imageMap.get(p.getId());
			ProductGetDTO pDto = new ProductGetDTO();
			BeanUtils.copyProperties(p, pDto);
			pDto.setBrand(brandName);
			pDto.setCategory(categoryName);
			pDto.setImage(image);
			return pDto;
			
		}).collect(Collectors.toList());
		
		return resultList;
	}
	
	private Integer getUserId() {
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails.getId().intValue();
	}
}
