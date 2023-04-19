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
	
	@JmsListener(destination = "orderqueue")
	public void receiveOrder(String orderMessage) {
		System.out.println("receiveOrder 1-.>>>>>>>>" + orderMessage);

		ObjectMapper mapper = new ObjectMapper();
				
	    try {
			Orders order = mapper.readValue(orderMessage, Orders.class);
			
			orderService.createOrders(order);
			System.out.println("receiveOrder------>" + order.getOrderNumber());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Orders order = orderMessage;
 	//	System.out.println("receiveOrder 2-.>>>>>>>>" + response);

 	}
}
