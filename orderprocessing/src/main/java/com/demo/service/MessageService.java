package com.demo.service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class MessageService {
	
	public String publishCreateOrder(JmsTemplate jmsTemplate, String queueName, String json) { 
			
		jmsTemplate.send(queueName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage object = session.createObjectMessage(json);
				return object;
			}
		});
		return "SUCCESS";
	}
}
