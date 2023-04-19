package com.demo.orderqueue;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.demo.orderentities.Orders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Publisher {

	@Autowired
	JmsTemplate jmsTemplate;
	
	public Orders publishOrder(JmsTemplate jmsTemplate, Orders order) {
		System.out.println("createorders-----" + order.getOrderNumber());
		StringBuilder json = new StringBuilder();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			 json.append(ow.writeValueAsString(order));
			System.out.println("json----->>>>" + json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		jmsTemplate.send("orderqueue", new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage object = session.createObjectMessage(json);
				
				System.out.println("createorders orderqueur-----" + json);

				return object;
			}
						
		});
					 
		return order;
	}
}
