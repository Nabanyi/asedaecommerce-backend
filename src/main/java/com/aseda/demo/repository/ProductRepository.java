package com.aseda.demo.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aseda.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findByUserIdAndStatus(Integer userId, String status);
	//Page<Product> findByUserIdAndStatus(Integer userId, String status, Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.id=:productId AND p.userId=:userId")
	Product findProductById(
			@Param("productId") Integer productId, 
			@Param("userId") Integer userId);
	
	List<Product> findByBrandOrCategory(Integer brandId, Integer categoryId);
	
    Page<Product> findByStatus(String status, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.status='Active' AND p.category IN :categories")
    Page<Product> findByCategory(@Param("categories") List<Integer> categories, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.status='Active' AND p.category IN :categories AND p.brand IN :brands")
    Page<Product> findByCategoryAndBrand(@Param("categories") List<Integer> categories, 
                                         @Param("brands") List<Integer> brands, 
                                         Pageable pageable);
}
