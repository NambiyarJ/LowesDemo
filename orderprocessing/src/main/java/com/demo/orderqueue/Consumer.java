package com.demo.orderqueue;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	@JmsListener(destination = "orderqueue")
	public void receiveOrder(String order) {
		System.out.println(order);
	}
}
