package com.demo.serializer;

import java.io.IOException;

import com.demo.model.Orders;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StringToObjectSerializer {
	public Orders getOrderObject(String orderMessage) {
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
