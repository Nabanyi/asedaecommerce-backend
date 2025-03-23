package com.aseda.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.aseda.demo.dto.CreateCategoryDTO;
import com.aseda.demo.dto.GetCategoryDTO;
import com.aseda.demo.repository.CategoryRepository;
import com.aseda.demo.entity.Category;
import com.aseda.demo.entity.CustomUserDetails;
import com.aseda.demo.exception.ResourceNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<GetCategoryDTO> getUserCategories(){
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Category> categories = categoryRepository.findAllByUserId(userDetails.getId().intValue());
		List<GetCategoryDTO> resultList = categories.stream().map(category -> new GetCategoryDTO(
				category.getId(),
				category.getUserId(),
				category.getName(),
				category.getDescription(),
				category.getStatus(),
				category.getCreatedAt()
				))
				.collect(Collectors.toList());
		return resultList;
	}
	
	public GetCategoryDTO getUserCategory(int categoryId){
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Category category = categoryRepository.findSingleCategory(userDetails.getId().intValue(), categoryId);
		
		GetCategoryDTO categoryDTO2 = new GetCategoryDTO();
		categoryDTO2.setId(category.getId());
		categoryDTO2.setUserId(category.getUserId());
		categoryDTO2.setName(category.getName());
		categoryDTO2.setDescription(category.getDescription());
		categoryDTO2.setStatus(category.getStatus());
		categoryDTO2.setCreatedAt(category.getCreatedAt());
		return categoryDTO2;
	}
	
	public GetCategoryDTO createCategory(CreateCategoryDTO categoryDTO) {
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Category category = new Category();
		category.setName(categoryDTO.getName());
		category.setDescription(categoryDTO.getDescription());
		category.setStatus(categoryDTO.getStatus());
		category.setUserId(userDetails.getId().intValue());
		categoryRepository.save(category);
		
		GetCategoryDTO categoryDTO2 = new GetCategoryDTO();
		categoryDTO2.setId(category.getId());
		categoryDTO2.setName(category.getName());
		categoryDTO2.setDescription(category.getDescription());
		categoryDTO2.setStatus(category.getStatus());
		categoryDTO2.setCreatedAt(category.getCreatedAt());
		return categoryDTO2;
	}
	
	public GetCategoryDTO updateCategory(Integer id, CreateCategoryDTO categoryDTO) {
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Failed to load category with Id:"+id));
		category.setName(categoryDTO.getName());
		category.setDescription(categoryDTO.getDescription());
		category.setStatus(categoryDTO.getStatus());
		category.setUserId(userDetails.getId().intValue());
		categoryRepository.save(category);
		
		GetCategoryDTO categoryDTO2 = new GetCategoryDTO();
		categoryDTO2.setId(id);
		categoryDTO2.setName(category.getName());
		categoryDTO2.setDescription(category.getDescription());
		categoryDTO2.setStatus(category.getStatus());
		categoryDTO2.setCreatedAt(category.getCreatedAt());
		return categoryDTO2;
	}
	
	public GetCategoryDTO deleteCategory(Integer id) {
		Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Failed to load category with Id:"+id));
		category.setStatus("Inactive");
		categoryRepository.save(category);
		
		GetCategoryDTO categoryDTO2 = new GetCategoryDTO();
		categoryDTO2.setId(id);
		categoryDTO2.setName(category.getName());
		categoryDTO2.setDescription(category.getDescription());
		categoryDTO2.setStatus(category.getStatus());
		categoryDTO2.setCreatedAt(category.getCreatedAt());
		return categoryDTO2;
	}
}
