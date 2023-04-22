package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.repository.OrderRepository;
import com.demo.model.Orders;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	public Orders createOrder(Orders order) {
		return orderRepository.save(order);
	}

	public Orders getOrdersById(String id) {
		return orderRepository.findById(id).get();
	}

	public Orders getOrdersByNumber(String orderNumber) {
		return orderRepository.findByOrderNumber(orderNumber);
	}

	public Orders updateOrderStatus(String orderNumber) {
		Orders order = getOrdersByNumber(orderNumber);
		order.setStatus("processed");
		orderRepository.save(order);
		return order;
	}
	
	public Orders deleteOrder(String orderNumber) {
		return orderRepository.deleteByOrderNumber(orderNumber);
	}

}
