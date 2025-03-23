package com.aseda.demo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aseda.demo.dto.ReviewCreateDTO;
import com.aseda.demo.entity.Review;
import com.aseda.demo.repository.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	
	public List<Review> getAllReviews(Integer productId) {
		
		return reviewRepository.findByProductId(productId);
	}
	
	public Review createReview(ReviewCreateDTO createDTO) {
		Review review = new Review();
		BeanUtils.copyProperties(createDTO, review);
		return reviewRepository.save(review);
	}
}
