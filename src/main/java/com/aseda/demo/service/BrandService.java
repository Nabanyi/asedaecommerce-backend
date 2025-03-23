package com.aseda.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.aseda.demo.dto.BrandUpdateDTO;
import com.aseda.demo.dto.BrandCreateDTO;
import com.aseda.demo.dto.BrandGetDTO;
import com.aseda.demo.entity.Brand;
import com.aseda.demo.entity.CustomUserDetails;
import com.aseda.demo.exception.ResourceNotFoundException;
import com.aseda.demo.repository.BrandRepository;

@Service
public class BrandService {

	@Autowired
	private BrandRepository brandRepository;
	
	private Integer getUserId() {
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails.getId().intValue();
	}
	
	public List<BrandGetDTO> getUserBrands(){
		List<Brand> brands = brandRepository.findAllByUserId(getUserId());
		List<BrandGetDTO> resultList = brands.stream().map(brand -> {
			BrandGetDTO gtBrandDTO = new BrandGetDTO();
			BeanUtils.copyProperties(brand, gtBrandDTO);
			return gtBrandDTO;
		}).collect(Collectors.toList());
		return resultList;
	}
	
	public BrandGetDTO getUserBrand(int brandId){
		Brand brand = brandRepository.findSingleBrand(getUserId(), brandId);
		BrandGetDTO brand2 = new BrandGetDTO();
		BeanUtils.copyProperties(brand, brand2);
		return brand2;
	}
	
	public BrandGetDTO createBrand(BrandCreateDTO brandDTO) {
		Brand brand = new Brand();
		BeanUtils.copyProperties(brandDTO, brand);
		brand.setUserId(getUserId());
		brandRepository.save(brand);
		
		BrandGetDTO brandDTO2 = new BrandGetDTO();
		BeanUtils.copyProperties(brand, brandDTO2);
		return brandDTO2;
	}

	public BrandGetDTO updateBrand(Integer id, BrandUpdateDTO brandDTO) {
		Brand brand = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Failed to load brand with Id:"+id));
		BeanUtils.copyProperties(brandDTO, brand);
		brand.setUserId(getUserId());
		brandRepository.save(brand);
		
		BrandGetDTO brandDTO2 = new BrandGetDTO();
		BeanUtils.copyProperties(brand, brandDTO2);
		return brandDTO2;
	}
	
	public BrandGetDTO deleteBrand(Integer id) {
		Brand brand = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Failed to load brand with Id:"+id));
		brand.setStatus("Inactive");
		brandRepository.save(brand);
		
		BrandGetDTO brandDTO2 = new BrandGetDTO();
		BeanUtils.copyProperties(brand, brandDTO2);
		return brandDTO2;
	}

}
