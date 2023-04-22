package com.demo.api;

import com.demo.app.App;
import com.demo.model.OrderLineItems;
import com.demo.model.Orders;
import com.demo.serializer.JsonSerializer;
import com.demo.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
@WithMockUser(roles = "ADMIN")
public class TestOrderAPIServices {

	private final MockMvc mockMvc;
	private final OrderService orderservice;
	private final ObjectMapper objectMapper;
	private final String orderNumber = "100021";

	@Autowired
	public TestOrderAPIServices(MockMvc mockMvc, OrderService orderservice, ObjectMapper objectMapper) {
		this.mockMvc = mockMvc;
		this.objectMapper = objectMapper;
		this.orderservice = orderservice;
	}

	@Test
	public void TestCreateOrderQueueAPISuccess() throws Exception {
		Orders order = getOrderData(orderNumber);
		MvcResult createResult = this.mockMvc
				.perform(post("/api/v1/orderqueue/publishCreateOrder")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonSerializer.toJson(objectMapper, order)))
				.andExpect(status().isOk())
				.andReturn();

	    Orders orderDetails = orderservice.getOrdersByNumber(orderNumber);

		System.out.println("TestCreateOrderQueueAPISuccess----->" + orderDetails.getOrderNumber());
		assertEquals("SUCCESS", createResult.getResponse().getContentAsString(), "publishCreateOrder API failed!!!");
		assertEquals(order.getOrderNumber(), orderDetails.getOrderNumber(), "Order Number update isn't applied!");
	}
	
	
//	@Test
//	public void TestCreateOrderQueueAPIFail() throws Exception {
//		Orders order = getOrderData();
//
//		System.out.println(JsonSerializer.toJson(objectMapper, order));
//		MvcResult createResult = this.mockMvc
//				.perform(post("/api/v1/orderqueue/publishCreateOrder")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(JsonSerializer.toJson(objectMapper, order)))
//				.andReturn();
//
//		System.out.println("TestCreateOrderQueueAPIFail----->" + createResult.getResponse().getContentAsString());
//		assertEquals("", createResult.getResponse().getContentAsString(), "publishCreateOrder API failed!!!");
//	}
	
	@Test
	public void TestUpdateOrderStatusQueueAPISuccess() throws Exception {
		
		MvcResult updateResult = this.mockMvc
			    .perform(put(String.format("/api/v1/orderqueue/publishUpdateOrder/%s", orderNumber))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		System.out.println("TestUpdateOrderStatusQueueAPISuccess----->" + updateResult.getResponse().getContentAsString());
		assertEquals("SUCCESS", updateResult.getResponse().getContentAsString(), "publishUpdateOrder API failed!!!");
	}
	
	@Test
	public void TestGetByOrderNumberSuccess() throws Exception {
		
 		MvcResult orderResult = this.mockMvc
				.perform(get(String.format("/api/v1/order/getByNumber/%s", orderNumber))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		Orders orderview = JsonSerializer.fromJson(objectMapper, orderResult.getResponse().getContentAsString(), Orders.class);
		System.out.println("TestGetByOrderNumberSuccess----->" + orderview.getOrderNumber());

		assertNotNull(orderview.getOrderNumber(), "Order Number must not be null!");
		assertEquals(orderNumber, orderview.getOrderNumber(), "Order Number update isn't applied!");
	}
	
//	@Test
//	public void TestDeleteOrderSuccess() throws Exception {
//
//		MvcResult orderResult = this.mockMvc
//		.perform(delete("/api/v1/order/deleteOrder/" + orderNumber)
//		.contentType(MediaType.APPLICATION_JSON))
//		.andExpect(status().isOk())
//		.andReturn();
//		
//		Orders orderview = JsonSerializer.fromJson(objectMapper, orderResult.getResponse().getContentAsString(), Orders.class);
//		System.out.println("TestDeleteOrderSuccess----->" + orderview.getOrderNumber());
//
//	}
	
	public Orders getOrderData(String orderNumber) {
		OrderLineItems lineitems = new OrderLineItems();
		lineitems.setLineItem("1");
		lineitems.setOrderQuantity("10");
		lineitems.setUnitPrice("20.50");
		lineitems.setUom("pc");

		List<OrderLineItems> listlineItems = new ArrayList<>();
		listlineItems.add(lineitems);

		Orders order = new Orders();
		order.setId(orderNumber);
		order.setOrderNumber(orderNumber);
		order.setStatus("placed");
		order.setLineItems(listlineItems);
		return order;
	}
	
	public Orders getOrderData() {
		return null;
	}
}
