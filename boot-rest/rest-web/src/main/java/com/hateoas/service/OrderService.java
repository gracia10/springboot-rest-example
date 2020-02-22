package com.hateoas.service;

import java.util.List;

import com.hateoas.domain.Order;

public interface OrderService {
	
	List<Order> getAllOrdersForCustomer(String customerId);
	
	Order getOrderByIdForCustomer(String customerId, String orderId);
}
