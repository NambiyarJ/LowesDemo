package com.demo.service;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.orderentities.Orders;
import com.demo.repositories.OrderRepository;
import com.demo.DBConfig.*;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	public Orders createOrders(Orders order) {
		return orderRepository.save(order);
 	}
	
	public Orders getOrdersById(String id) {
		return orderRepository.findById(id).get();
	}
	 
	public Orders getOrdersByNumber(String orderNumber) {
		return  orderRepository.findByorderNumber(orderNumber);
	}
	
	public Orders updateOrderStatus(Orders order) {
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(DBConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		
		Query updateQuery = new Query();
		updateQuery.addCriteria(Criteria.where("orderNumber").is(order.getOrderNumber()));

		Orders updateOrder = mongoOperation.findOne(updateQuery, Orders.class);

		System.out.println("updateQuery - " + updateOrder.getStatus());

		updateOrder.setStatus(order.getStatus());
		return mongoOperation.save(updateOrder);
	}
	 
}
	