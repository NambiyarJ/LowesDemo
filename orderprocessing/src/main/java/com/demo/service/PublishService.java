package com.demo.service;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import com.demo.model.Orders;
import com.demo.serializer.ObjectToJsonSerializer;
  
public class PublishService {
	@Autowired
	JmsTemplate jmsTemplate;
	
	public String publishCreateOrder(JmsTemplate jmsTemplate, Orders order) {
		ObjectToJsonSerializer objToJson = new ObjectToJsonSerializer(); 
		MessageService messageService = new MessageService();
		return messageService.publishCreateOrder(jmsTemplate, "createorderqueue", objToJson.getObjectToJson(order));
	}
	
	public String publishUpdateOrder(JmsTemplate jmsTemplate, String orderNumber) { 
		MessageService messageService = new MessageService();
		return messageService.publishCreateOrder(jmsTemplate, "updateorderqueue", orderNumber);
	}
}
