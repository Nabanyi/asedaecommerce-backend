package com.aseda.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aseda.demo.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	@Query("SELECT c FROM Category c WHERE c.userId = :userId")
	List<Category> findAllByUserId(@Param("userId") Integer userId);
	
	@Query("SELECT c FROM Category c WHERE c.userId = :userId and c.id = :categoryId")
	Category findSingleCategory(@Param("userId") Integer userId, @Param("categoryId")Integer categoryId);

}
