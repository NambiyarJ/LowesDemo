package com.demo.orderqueue;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.orderentities.Orders;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/orderqueue")
public class PublishController {
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@PostMapping("/publishCreateOrder")
	public Orders createOrders(@RequestBody Orders order) {
		Publisher publishObj = new Publisher(); 
		return publishObj.publishCreateOrder(jmsTemplate, order);
	} 
	
	@PutMapping("/publishUpdateOrder")
	public String updateOrders(@RequestParam("orderNumber") String orderNumber) {
		Publisher publishObj = new Publisher(); 
		return publishObj.publishUpdateOrder(jmsTemplate, orderNumber);
	}
	
}
