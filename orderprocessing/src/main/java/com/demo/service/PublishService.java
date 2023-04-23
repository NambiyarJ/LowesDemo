package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.Orders;
import com.demo.serializer.JsonSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class PublishService {

	private final MessageService messageService;
	private final JsonSerializer jsonSerializer;

	@Autowired
	public PublishService(MessageService messageService, JsonSerializer jsonSerializer) {
		this.messageService = messageService;
		this.jsonSerializer = jsonSerializer;
	}

	public String publishCreateOrder(Orders order) throws JsonProcessingException {
		return messageService.publishMessages("createorderqueue", jsonSerializer.toJson(order));
	}

	public String publishUpdateOrder(String orderNumber) {
		return messageService.publishMessages("updateorderqueue", orderNumber);
	}
}
