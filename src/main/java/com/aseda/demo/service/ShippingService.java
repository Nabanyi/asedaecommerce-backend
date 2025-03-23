package com.aseda.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

import com.aseda.demo.dto.ShippingCreateDTO;
import com.aseda.demo.dto.ShippingGetDTO;
import com.aseda.demo.dto.ShippingUpdateDTO;
import com.aseda.demo.entity.CustomUserDetails;
import com.aseda.demo.entity.Shipping;
import com.aseda.demo.exception.ResourceNotFoundException;
import com.aseda.demo.repository.ShippingRepository;

@Service
public class ShippingService {

	@Autowired
	private ShippingRepository shippingRepository;
	
	public List<ShippingGetDTO> getAllShipping(){
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Shipping> allShippings = shippingRepository.findAllShippingByUserId(userDetails.getId().intValue());
		List<ShippingGetDTO> resultDtos = allShippings.stream().map(this::toShippingGetDTO).collect(Collectors.toList());
		return resultDtos;
	}
	
	public ShippingGetDTO getShipping(Integer shippingId) {
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Shipping shipping = shippingRepository.findSingleShippingByUserId(userDetails.getId().intValue(), shippingId);
		return toShippingGetDTO(shipping);
	}
	
	private ShippingGetDTO toShippingGetDTO(Shipping shipping) {
	    ShippingGetDTO dto = new ShippingGetDTO();
	    BeanUtils.copyProperties(shipping, dto);
	    return dto;
	}
	
	public ShippingGetDTO createShipping(ShippingCreateDTO createDTO) {
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    Shipping shipping = new Shipping();
	    BeanUtils.copyProperties(createDTO, shipping);
	    shipping.setUserId(userDetails.getId().intValue());
	    Shipping createdShipping = shippingRepository.save(shipping);
	    ShippingGetDTO getDTO = new ShippingGetDTO();
	    BeanUtils.copyProperties(createdShipping, getDTO);
	    return getDTO;
	}
	
	public void updateShipping(Integer shippingId, ShippingUpdateDTO updateDTO) {
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Shipping shipping = new Shipping();
		BeanUtils.copyProperties(updateDTO, shipping);
		shipping.setUserId(userDetails.getId().intValue());
		shipping.setId(shippingId);
		shippingRepository.save(shipping);
	}
	
	public void deleteShipping(Integer shippingId) {
		Shipping shipping = shippingRepository.findById(shippingId).orElseThrow(() -> new ResourceNotFoundException("Failed to load shipping address with Id:"+shippingId));
		shippingRepository.delete(shipping);
	}
	
}
