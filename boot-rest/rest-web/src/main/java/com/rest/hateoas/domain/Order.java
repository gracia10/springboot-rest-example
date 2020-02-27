package com.rest.hateoas.domain;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Order extends RepresentationModel<Order>{

	private String orderId;
	private double price;
	private int quantity;
}
