package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.Orders;
import com.demo.serializer.JsonSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PublishService {

	@Autowired
	MessageService messageService;
	
	@Autowired
	ObjectMapper objectMapper;
	 
	public String publishCreateOrder(Orders order) throws JsonProcessingException {
		return messageService.publishMessages("createorderqueue", JsonSerializer.toJson(objectMapper, order));
	}

	public String publishUpdateOrder(String orderNumber) {
		return messageService.publishMessages("updateorderqueue", orderNumber);
	}
}
