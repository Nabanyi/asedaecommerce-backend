package com.aseda.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aseda.demo.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
	
	List<Orders> findByUserId(Integer userId);
	List<Orders> findByOrderIdIn(List<Integer> orderIds);
	boolean existsById(Integer id);
}
