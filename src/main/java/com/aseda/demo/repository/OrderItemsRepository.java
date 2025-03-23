package com.aseda.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aseda.demo.entity.OrderItem;
import java.util.List;


public interface OrderItemsRepository extends JpaRepository<OrderItem, Integer> {

	List<OrderItem> findByOrderId(Integer orderId);
	
	List<OrderItem> findByOrderBy(Integer orderBy);
	
	List<OrderItem> findByUserId(Integer userId);
	
}
