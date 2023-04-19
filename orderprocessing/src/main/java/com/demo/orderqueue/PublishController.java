package com.demo.orderqueue;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.orderentities.Orders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/orderqueue")
public class PublishController {
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	Publisher publishObj = new Publisher();
	
//	@GetMapping("/publish/{message}")
//	public String publish(@PathVariable("message") String content) {
//		
//		jmsTemplate.send("orderqueue", new MessageCreator() {
//
//			@Override
//			public Message createMessage(Session session) throws JMSException {
//				ObjectMessage object = session.createObjectMessage(content);
//				return object;
//			}
//			
//		});
//		 
//		return content;
//	}
	
	@PostMapping("/publish")
	public Orders createOrders(@RequestBody Orders order) {
		
//		System.out.println("createorders-----" + order.getOrderNumber());
//		StringBuilder json = new StringBuilder();
//		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//		try {
//			 json.append(ow.writeValueAsString(order));
//			System.out.println("json----->>>>" + json);
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		jmsTemplate.send("orderqueue", new MessageCreator() {
//			
//			@Override
//			public Message createMessage(Session session) throws JMSException {
//				ObjectMessage object = session.createObjectMessage(json);
//				
//				System.out.println("createorders orderqueur-----" + json);
//
//				return object;
//			}
//						
//		});
//					 
//		return order;
			
		return publishObj.publishOrder(jmsTemplate, order);
	} 
	
	
}
