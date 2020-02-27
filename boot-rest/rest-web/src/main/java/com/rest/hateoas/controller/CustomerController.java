package com.rest.hateoas.controller;

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

import com.rest.hateoas.domain.Customer;
import com.rest.hateoas.domain.Order;
import com.rest.hateoas.service.CustomerService;
import com.rest.hateoas.service.OrderService;


@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CustomerService customerService;
	
	//유저목록
	@GetMapping(produces = {"application/hal+json"})
	public CollectionModel<Customer> getAllCustomers(){
		List<Customer> allCustomers = customerService.allCustomers();
		
		for(Customer customer: allCustomers) {
			String customerId = customer.getCustomerId();
			Link link = linkTo(methodOn(CustomerController.class).getCustomerById(customerId)).withSelfRel();
			customer.add(link);
			
			if(orderService.getAllOrdersForCustomer(customerId).size() > 0) {
				Link ordersLink = linkTo(methodOn(CustomerController.class).getOrdersForCustomer(customerId)).withRel("allOrders");
				customer.add(ordersLink);
			}
		}
		
		Link link = linkTo(methodOn(CustomerController.class).getAllCustomers()).withSelfRel();
		CollectionModel<Customer> result = new CollectionModel<Customer>(allCustomers,link);
		return result;
	}
	
	//유저상세
	@GetMapping("/{customerId}")
	public Customer getCustomerById(@PathVariable String customerId) {
		return customerService.getCustomerDetail(customerId);
	}
	
	//오더목록
	@GetMapping(value= "/{customerId}/orders", produces = {"application/hal+json"})
	public CollectionModel<Order> getOrdersForCustomer(@PathVariable String customerId){
		List<Order> orders = orderService.getAllOrdersForCustomer(customerId);
		for(Order order: orders) {
			String orderId = order.getOrderId();
			Link link = linkTo(methodOn(CustomerController.class).getOrderById(customerId, orderId)).withSelfRel();
			order.add(link);
		}
		
		Link link = linkTo(methodOn(CustomerController.class).getOrdersForCustomer(customerId)).withSelfRel();
		CollectionModel<Order> result = new CollectionModel<Order>(orders, link);
		return result;
	}

	//오더상세
	@GetMapping("/{customerId}/{orderId}")
	public Order getOrderById(@PathVariable String customerId,@PathVariable String orderId) {
		return orderService.getOrderByIdForCustomer(customerId, orderId);
	}
	
}










