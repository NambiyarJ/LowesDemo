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
public class TestMessagePublishApi {

	private final MockMvc mockMvc;
	private final OrderService orderservice;
	private final ObjectMapper objectMapper;

	@Autowired
	public TestMessagePublishApi(MockMvc mockMvc, OrderService orderservice, ObjectMapper objectMapper) {
		this.mockMvc = mockMvc;
		this.objectMapper = objectMapper;
		this.orderservice = orderservice;
	}

	@Test
	public void TestOrderUpdateStatus() throws Exception {
		final String orderNumber = "100011";
		Orders order = getOrderData(orderNumber);

		orderservice.createOrders(order);

		MvcResult createResult = this.mockMvc
				.perform(get(String.format("/api/v1/order/getByNumber/%s", orderNumber))
						.contentType(MediaType.APPLICATION_JSON).content(JsonSerializer.toJson(objectMapper, order)))
				.andExpect(status().isOk()).andReturn();

		Orders orderview = JsonSerializer.fromJson(objectMapper, createResult.getResponse().getContentAsString(), Orders.class);
		assertNotNull(orderview.getOrderNumber(), "Author id must not be null!");
		assertEquals(order.getOrderNumber(), orderview.getOrderNumber(), "Author name update isn't applied!");

	}

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
}
