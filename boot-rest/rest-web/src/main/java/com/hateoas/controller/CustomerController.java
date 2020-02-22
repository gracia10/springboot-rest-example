package com.hateoas.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hateoas.domain.Order;
import com.hateoas.service.OrderService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping(value="/{cuctomerId}/{orderId}")
	public Order getOrderById(@PathVariable final String customerId,@PathVariable final String orderId) {
		return orderService.getOrderByIdForCustomer(customerId, orderId);
	}
	
	@GetMapping(value="/{customerId}/orders", produces = "application/hal+json")
	public void getOrdersForCustomer(@PathVariable final String customerId) {
		List<Order> orders = orderService.getAllOrdersForCustomer(customerId);
		for (final Order order : orders) {
			Link selfLink = linkTo(methodOn(CustomerController.class).getOrderById(customerId, order.getOrderId())).withSelfRel();
			order.add(selfLink);
		}
	}
	
}
