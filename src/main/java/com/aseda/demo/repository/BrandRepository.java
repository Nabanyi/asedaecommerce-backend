package com.aseda.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aseda.demo.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
	@Query("SELECT b FROM Brand b WHERE b.userId = :userId")
	List<Brand> findAllByUserId(@Param("userId") Integer userId);
	
	@Query("SELECT b FROM Brand b WHERE b.userId = :userId and b.id = :brandId")
	Brand findSingleBrand(@Param("userId") Integer userId, @Param("brandId")Integer brandId);
}
