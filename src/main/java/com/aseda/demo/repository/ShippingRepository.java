package com.aseda.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aseda.demo.entity.Shipping;

public interface ShippingRepository extends JpaRepository<Shipping, Integer> {

	@Query("SELECT s FROM Shipping s WHERE s.userId = :userId")
	List<Shipping> findAllShippingByUserId(@Param("userId") Integer userId);
	
	@Query("SELECT s FROM Shipping s WHERE s.userId = :userId AND s.id = :shippingId")
	Shipping findSingleShippingByUserId(
			@Param("userId") Integer userId, 
			@Param("shippingId") Integer shippingId
			);
	
	List<Shipping> findByIdIn(List<Integer> addressIds);
}
