package com.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.model.Orders;

public interface OrderRepository extends MongoRepository<Orders, String> {
	
	Orders findByOrderNumber(String orderNumber);
	
	Orders deleteByOrderNumber(String orderNumber);
	
}
