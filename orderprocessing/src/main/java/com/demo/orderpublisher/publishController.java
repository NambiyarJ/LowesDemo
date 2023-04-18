package com.demo.orderpublisher;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;


@RestController
@RequestMapping("/api/v1")
public class publishController {
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	
	@GetMapping("/publish/{message}")
	public String publish(@PathVariable("message") String content) {
		
		jmsTemplate.send("orderqueue", new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage object = session.createObjectMessage(content);
				return object;
			}
			
		});
		 
		
		return content;
	}
 
}
