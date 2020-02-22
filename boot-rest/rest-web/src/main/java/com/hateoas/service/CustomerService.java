package com.hateoas.service;

import java.util.List;

import com.hateoas.domain.Customer;

public interface CustomerService {

	List<Customer> allCustomers();
	
	Customer getCustomerDetail(final String customerId);
}
