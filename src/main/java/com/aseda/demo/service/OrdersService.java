package com.aseda.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.aseda.demo.dto.OrderGetDTO;
import com.aseda.demo.dto.ShippingGetDTO;
import com.aseda.demo.dto.OrderItemsDTO;
import com.aseda.demo.entity.CustomUserDetails;
import com.aseda.demo.entity.OrderItem;
import com.aseda.demo.entity.Orders;
import com.aseda.demo.entity.Product;
import com.aseda.demo.entity.Shipping;
import com.aseda.demo.entity.User;
import com.aseda.demo.exception.ResourceNotFoundException;
import com.aseda.demo.repository.OrderItemsRepository;
import com.aseda.demo.repository.OrdersRepository;
import com.aseda.demo.repository.ProductRepository;
import com.aseda.demo.repository.ShippingRepository;
import com.aseda.demo.repository.UserRepository;
import com.aseda.demo.requestobjs.CreateOrderRequest;

@Service
public class OrdersService {

	@Autowired
	private OrdersRepository ordersRepository;
	
	@Autowired
	private OrderItemsRepository itemsRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ShippingRepository shippingRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	private static final int MIN = 1000000;
    private static final int MAX = 9999999;
    private final Random random = new Random();
	
	public List<OrderGetDTO> getOrdersPlaced(){
		List<Orders> orders = ordersRepository.findByUserId(getUserId());
		List<OrderItem> items = itemsRepository.findByOrderBy(getUserId());
		
		List<Product> products = productRepository.findAll();
		Map<Integer, String> productsMap = products.stream().collect(Collectors.toMap(Product::getId, Product::getName));
		
		Map<Integer, List<OrderItemsDTO>> orderItemsMap = items.stream()
			    .collect(Collectors.groupingBy(
			        OrderItem::getOrderId,
			        Collectors.mapping(orderItem -> {
			            OrderItemsDTO dto = new OrderItemsDTO();
			            dto.setItem(productsMap.get(orderItem.getProductId()));
			            dto.setQuantity(orderItem.getQuantity());
			            dto.setPrice(orderItem.getPrice());
			            dto.setTotal(orderItem.getTotal());
			            dto.setTransDate(orderItem.getTransDate());
			            return dto;
			        }, Collectors.toList())
			    ));
		
		List<Shipping> addresses = shippingRepository.findAllShippingByUserId(getUserId());
		Map<Integer, ShippingGetDTO> addressMap = addresses.stream().map(address -> {
			ShippingGetDTO shippingGetDTO = new ShippingGetDTO();
			shippingGetDTO.setId(address.getId());
			shippingGetDTO.setCountry(address.getCountry());
			shippingGetDTO.setRegion(address.getRegion());
			shippingGetDTO.setLocation(address.getLocation());
			shippingGetDTO.setAddress(address.getAddress());
			shippingGetDTO.setStreet(address.getStreet());
			shippingGetDTO.setLandmark(address.getLandmark());
			return shippingGetDTO;
		}).collect(Collectors.toMap(ShippingGetDTO::getId, shippingGetDTO -> shippingGetDTO));
		
		List<OrderGetDTO> resultList = orders.stream().map(order -> {
			OrderGetDTO orderGetDTO = new OrderGetDTO();
			orderGetDTO.setOrderid(order.getOrderId());
			orderGetDTO.setTransDate(order.getTransDate());
			orderGetDTO.setTotalAmount(order.getTotalAmount());
			orderGetDTO.setStatus(order.getStatus());
			
			ShippingGetDTO address = addressMap.get(order.getAddressId());
			orderGetDTO.setAddress(address);
			orderGetDTO.setItems(orderItemsMap.get(order.getOrderId()));
			
			return orderGetDTO;
		}).collect(Collectors.toList());
		
		return resultList;
	}
	
	public List<OrderGetDTO> getOrdersReceived(){
		List<Orders> orders = ordersRepository.findAll();
		
		//prepare customer data
		List<Long> customerIds = new ArrayList<>();
		for (Orders order : orders) {
			customerIds.add(order.getUserId().longValue());
		}
		List<User> customers = userRepository.findByIdIn(customerIds);
		Map<Long, String> customersMap = customers.stream()
			    .collect(Collectors.toMap(
			        User::getId, 
			        user -> user.getFirstName() + " " + user.getLastName()
			    ));
		
		List<Product> products = productRepository.findAll();
		Map<Integer, String> productsMap = products.stream().collect(Collectors.toMap(Product::getId, Product::getName));
		
		// prepare addresses
		List<Integer> addressIds = new ArrayList<>();
		for (Orders order : orders) {
			addressIds.add(order.getAddressId());
		}
		
		List<Shipping> shippings = shippingRepository.findByIdIn(addressIds);
		Map<Integer, ShippingGetDTO> addressMap = shippings.stream().map(address -> {
			ShippingGetDTO shippingGetDTO = new ShippingGetDTO();
			shippingGetDTO.setId(address.getId());
			shippingGetDTO.setCountry(address.getCountry());
			shippingGetDTO.setRegion(address.getRegion());
			shippingGetDTO.setLocation(address.getLocation());
			shippingGetDTO.setAddress(address.getAddress());
			shippingGetDTO.setStreet(address.getStreet());
			shippingGetDTO.setLandmark(address.getLandmark());
			return shippingGetDTO;
		}).collect(Collectors.toMap(ShippingGetDTO::getId, shippingGetDTO -> shippingGetDTO));
		
		List<OrderItem> items = itemsRepository.findAll();
		Map<Integer, List<OrderItemsDTO>> orderItemsMap = items.stream()
			    .collect(Collectors.groupingBy(
			        OrderItem::getOrderId,
			        Collectors.mapping(orderItem -> {
			            OrderItemsDTO dto = new OrderItemsDTO();
			            dto.setItem(productsMap.get(orderItem.getProductId()));
			            dto.setQuantity(orderItem.getQuantity());
			            dto.setPrice(orderItem.getPrice());
			            dto.setTotal(orderItem.getTotal());
			            dto.setTransDate(orderItem.getTransDate());
			            return dto;
			        }, Collectors.toList())
			    ));
		
		List<OrderGetDTO> resultList = orders.stream().map(order -> {
			OrderGetDTO orderGetDTO = new OrderGetDTO();
			orderGetDTO.setOrderid(order.getOrderId());
			orderGetDTO.setTransDate(order.getTransDate());
			orderGetDTO.setTotalAmount(order.getTotalAmount());
			orderGetDTO.setCustomer(customersMap.get(order.getUserId().longValue()));
			orderGetDTO.setStatus(order.getStatus());
			
			ShippingGetDTO address = addressMap.get(order.getAddressId());
			orderGetDTO.setAddress(address);
			orderGetDTO.setItems(orderItemsMap.get(order.getOrderId()));
			
			return orderGetDTO;
		}).collect(Collectors.toList());
		
		return resultList;
	}
	
	public Integer generateUniqueOrderId() {
        Integer orderId;
        do {
            orderId = MIN + random.nextInt(MAX - MIN + 1);
        } while (ordersRepository.existsById(orderId));

        return orderId;
    }
	
	public void createOrder(CreateOrderRequest request) {
		List<Integer> ids = request.getProductIds();
		List<Double> qtys = request.getQuantities();
		
		if (ids.isEmpty() || qtys.isEmpty() || ids.size() != qtys.size()) {
	        throw new IllegalArgumentException("Invalid request: Product IDs and Quantities must be non-empty and of equal size.");
	    }
		
		Integer orderId = generateUniqueOrderId();
		
		List<OrderItem> orderItems = new ArrayList<>();
		Double orderTotal = 0.0;
		for (int i = 0; i < ids.size(); i++) {
			Integer id = ids.get(i);
			Double qty = qtys.get(i);
			
			Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No product found for Id"+id));
			Double total = qty * product.getPrice();
			
			orderTotal += total;

			OrderItem item = new OrderItem();
			item.setUserId(product.getUserId());
			item.setOrderBy(getUserId());
			item.setOrderId(orderId);
			item.setPrice(product.getPrice());
			item.setProductId(id);
			item.setQuantity(qty);
			item.setTotal(total);
			item.setTransDate(LocalDate.now());
			
			orderItems.add(item);
		}
		
		itemsRepository.saveAll(orderItems);
		
		Orders order = new Orders();
		order.setAddressId(request.getAddress());
		order.setOrderId(orderId);
		order.setTotalAmount(orderTotal);
		order.setUserId(getUserId());
		order.setTransDate(LocalDate.now());
		order.setStatus("Active");
		ordersRepository.save(order);
	}
	
	private Integer getUserId() {
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails.getId().intValue();
	}
}
