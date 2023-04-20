package com.demo.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.demo.model.Orders;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ConsumerService {
	@Autowired
	OrderService orderService;
	
	@JmsListener(destination = "createorderqueue")
	public void receiveCreateOrder(String orderMessage) {
		ObjectMapper mapper = new ObjectMapper();	
	    try {
			Orders order = mapper.readValue(orderMessage, Orders.class);
			orderService.createOrders(order);
 		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
 	}
	
	@JmsListener(destination = "updateorderqueue")
	public void receiveUpdateOrder(String orderNumber) { 
		try {
			orderService.updateOrderStatus(orderNumber);
		} catch (Exception e) {
		}
 	}
}
