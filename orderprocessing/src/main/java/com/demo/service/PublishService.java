package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import com.demo.model.Orders;
import com.demo.serializer.JsonSerializer;

public class PublishService {
	@Autowired
	JmsTemplate jmsTemplate;

	MessageService messageService = new MessageService();
	
	public String publishCreateOrder(JmsTemplate jmsTemplate, Orders order) {
 		return messageService.publishCreateOrder(jmsTemplate, "createorderqueue", JsonSerializer.getObjectToJson(order));
	}

	public String publishUpdateOrder(JmsTemplate jmsTemplate, String orderNumber) {
 		return messageService.publishCreateOrder(jmsTemplate, "updateorderqueue", orderNumber);
	}
}
