package com.demo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;

@Document(collection = "orders")
public class Orders implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String orderNumber;

	private String status;

	private List<OrderLineItems> lineItems;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OrderLineItems> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<OrderLineItems> lineItems) {
		this.lineItems = lineItems;
	}
}
