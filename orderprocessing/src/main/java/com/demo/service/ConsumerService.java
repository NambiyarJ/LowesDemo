package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.demo.serializer.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ConsumerService {
	@Autowired
	OrderService orderService;

	@Autowired
	static ObjectMapper objectMapper;
	
	@JmsListener(destination = "createorderqueue")
	public void receiveCreateOrder(String orderMessage) {
		orderService.createOrder(JsonSerializer.getOrderObject(orderMessage));
	}

	@JmsListener(destination = "updateorderqueue")
	public void receiveUpdateOrder(String orderNumber) {
		orderService.updateOrderStatus(orderNumber);
	}
}
