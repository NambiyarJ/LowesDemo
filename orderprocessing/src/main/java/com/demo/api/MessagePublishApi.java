package com.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Orders;
import com.demo.service.PublishService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/orderqueue")
public class MessagePublishApi {
	@Autowired
	JmsTemplate jmsTemplate;

	@PostMapping("/publishCreateOrder")
	public String createOrders(@RequestBody Orders order) {
		PublishService publishObj = new PublishService();
		return publishObj.publishCreateOrder(jmsTemplate, order);
	}

	@PutMapping("/publishUpdateOrder")
	public String updateOrders(@RequestParam("orderNumber") String orderNumber) {
		PublishService publishObj = new PublishService();
		return publishObj.publishUpdateOrder(jmsTemplate, orderNumber);
	}
}
