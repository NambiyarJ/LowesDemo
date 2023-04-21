package com.demo.model;

public class OrderLineItems {
	
	private String lineItem;

	private String orderQuantity;

	private String uom;

	private String unitPrice;

	public String getLineItem() {
		return lineItem;
	}

	public void setLineItem(String lineItem) {
		this.lineItem = lineItem;
	}

	public String getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(String orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

 

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

}
