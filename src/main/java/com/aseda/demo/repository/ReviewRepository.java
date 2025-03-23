package com.aseda.demo.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aseda.demo.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

	List<Review> findByProductId(Integer productId);
}
