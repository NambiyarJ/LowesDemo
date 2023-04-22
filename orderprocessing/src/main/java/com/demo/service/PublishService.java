package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.demo.model.Orders;
import com.demo.serializer.JsonSerializer;

@Service
public class PublishService {
	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	MessageService messageService;

	public String publishCreateOrder(JmsTemplate jmsTemplate, Orders order) {
		return messageService.publishMessages(jmsTemplate, "createorderqueue", JsonSerializer.getObjectToJson(order));
	}

	public String publishUpdateOrder(JmsTemplate jmsTemplate, String orderNumber) {
		return messageService.publishMessages(jmsTemplate, "updateorderqueue", orderNumber);
	}
}
