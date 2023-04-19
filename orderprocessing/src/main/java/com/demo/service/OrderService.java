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
		System.out.println("order->>>>" + order);
		return orderRepository.save(order);
 	}
	
	public Orders getOrdersById(String id) {
		System.out.println("id->>>>" + id);
		return orderRepository.findById(id).get();
	}
	 
	public Orders getOrdersByNumber(String orderNumber) {
		System.out.println(orderNumber);
		return  orderRepository.findByorderNumber(orderNumber);
	}
}
	