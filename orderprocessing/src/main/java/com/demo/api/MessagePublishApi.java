package com.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Orders;
import com.demo.service.PublishService;
import com.fasterxml.jackson.core.JsonProcessingException;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/orderqueue")
public class MessagePublishApi {

	@Autowired
	PublishService publishObj;

	@PostMapping("/publishCreateOrder")
	public String createOrders(@RequestBody Orders order) throws JsonProcessingException {
		return publishObj.publishCreateOrder(order);
	}

	@PutMapping("/publishUpdateOrder/{orderNumber}")
	public String updateOrders(@PathVariable("orderNumber") String orderNumber) {
		return publishObj.publishUpdateOrder(orderNumber);
	}
}
