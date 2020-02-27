package com.rest.hateoas.service;

import java.util.List;

import com.rest.hateoas.domain.Order;

public interface OrderService {
	
	List<Order> getAllOrdersForCustomer(String customerId);
	
	Order getOrderByIdForCustomer(String customerId, String orderId);
}
