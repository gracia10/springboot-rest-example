package com.rest.hateoas.service;

import java.util.List;

import com.rest.hateoas.domain.Customer;

public interface CustomerService {

	List<Customer> allCustomers();
	
	Customer getCustomerDetail(final String customerId);
}
