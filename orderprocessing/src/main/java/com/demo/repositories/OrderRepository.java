package com.demo.repositories;

 
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.orderentities.Orders;

@Repository
public interface OrderRepository extends MongoRepository<Orders, String> {
	Orders findByorderNumber(String orderNumber);
}
