package com.demo.orderpublisher;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class consumer {

	@JmsListener(destination = "orderqueue")
	public void receiveOrder(String order) {
		System.out.println(order);
	}
}
