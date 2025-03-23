package com.aseda.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aseda.demo.entity.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {

	List<ProductImage> findAllByProductId(Integer productId);
	
	@Query("SELECT pi FROM ProductImage pi WHERE pi.id IN (SELECT MIN(pi2.id) FROM ProductImage pi2 GROUP BY pi2.productId)")
	List<ProductImage> findSingleRowForImages();

	ProductImage findByImage(String imageName);
	
	Optional<ProductImage> findFirstByProductId(Integer productId);
}
