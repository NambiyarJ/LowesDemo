package com.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Orders;

@Repository
public interface OrderRepository extends MongoRepository<Orders, String> {

	Orders findByOrderNumber(String orderNumber);

	Orders deleteByOrderNumber(String orderNumber);

}
