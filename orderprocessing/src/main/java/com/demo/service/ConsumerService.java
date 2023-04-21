package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.demo.serializer.StringToObjectSerializer;

@Component
public class ConsumerService {
	@Autowired
	OrderService orderService;

	@JmsListener(destination = "createorderqueue")
	public void receiveCreateOrder(String orderMessage) {
		StringToObjectSerializer strToObj = new StringToObjectSerializer();
		orderService.createOrders(strToObj.getOrderObject(orderMessage));
	}

	@JmsListener(destination = "updateorderqueue")
	public void receiveUpdateOrder(String orderNumber) {
		try {
			orderService.updateOrderStatus(orderNumber);
		} catch (Exception e) {
		}
	}
}
