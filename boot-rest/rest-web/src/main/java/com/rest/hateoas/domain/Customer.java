package com.rest.hateoas.domain;

import java.util.Map;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class Customer extends RepresentationModel<Customer> {
	
    private String customerId;
    private String customerName;
    private String companyName;
    private Map<String, Order> orders;
    
    public Customer(final String customerId, final String customerName, final String companyName) {
        super();
        this.customerId = customerId;
        this.customerName = customerName;
        this.companyName = companyName;
    }
}
