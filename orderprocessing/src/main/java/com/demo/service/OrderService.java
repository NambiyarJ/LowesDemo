package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.orderentities.Orders;
import com.demo.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	public Orders createOrders(Orders order) {
		return orderRepository.save(order);
 	}
	  
}
