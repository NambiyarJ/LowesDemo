package com.demo.service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
	@Autowired
	JmsTemplate jmsTemplate;

	public String publishMessages(String queueName, String json) {

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
