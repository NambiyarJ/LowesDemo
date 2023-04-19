package com.demo.orderqueue;

 
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.demo.orderentities.Orders;
import com.demo.service.OrderService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Consumer {
  
	@Autowired
	OrderService orderService;
	
	@JmsListener(destination = "createorderqueue")
	public void receiveCreateOrder(String orderMessage) {
		System.out.println("receiveOrder 1-.>>>>>>>>" + orderMessage);

		ObjectMapper mapper = new ObjectMapper();	
	    try {
			Orders order = mapper.readValue(orderMessage, Orders.class);
			
			orderService.createOrders(order);
			System.out.println("receiveOrder------>" + order.getOrderNumber());
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
		orderService.updateOrderStatus(orderNumber);
 	}
}
