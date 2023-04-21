package com.demo.serializer;

import java.io.IOException;

import com.demo.model.Orders;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JsonSerializer {

	public static String getObjectToJson(Orders order) {
		StringBuilder json = new StringBuilder();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			json.append(ow.writeValueAsString(order));
			// System.out.println("json----->>>>" + json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json.toString();
	}
	
	public static <T> String toJson(ObjectMapper objectMapper, T object) throws JsonProcessingException {
		return objectMapper.writeValueAsString(object);
	}

	public static <T> T fromJson(ObjectMapper objectMapper, String payload, Class<T> clazz)
			throws JsonProcessingException {
		return objectMapper.readValue(payload, clazz);
	}

	public static <T> T fromJson(ObjectMapper objectMapper, String payload, TypeReference<T> clazz)
			throws JsonProcessingException {
		return objectMapper.readValue(payload, clazz);
	}
	
	public static Orders getOrderObject(String orderMessage) {
		ObjectMapper mapper = new ObjectMapper();
		Orders order = null;
		try {
			order = mapper.readValue(orderMessage, Orders.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return order;
	}
}
