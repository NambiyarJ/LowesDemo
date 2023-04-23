package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.demo.model.Orders;
import com.demo.serializer.JsonSerializer;

@Service
public class ConsumerService {
	@Autowired
	OrderService orderService;

	@Autowired
	JsonSerializer jsonSerializer;

	@JmsListener(destination = "createorderqueue")
	public void receiveCreateOrder(String orderMessage) {
		orderService.createOrder(jsonSerializer.fromJson(orderMessage, Orders.class));
	}

	@JmsListener(destination = "updateorderqueue")
	public void receiveUpdateOrder(String orderNumber) {
		orderService.updateOrderStatus(orderNumber);
	}
}
