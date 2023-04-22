package com.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Orders;
import com.demo.service.OrderService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/order")
public class OrderApi {

	@Autowired
	OrderService orderService;

	@PostMapping("/create")
	public Orders createOrders(@RequestBody Orders order) {
		return orderService.createOrders(order);
	}

	@GetMapping("/getById/{id}")
	public Orders getOrdersById(@PathVariable("id") String id) {
		return orderService.getOrdersById(id);
	}

	@GetMapping("/getByNumber/{orderNumber}")
	public Orders getOrdersByNumber(@PathVariable("orderNumber") String orderNumber) {
		return orderService.getOrdersByNumber(orderNumber);
	}

	@PutMapping("/updateStatus/{orderNumber}")
	public Orders updateOrderStatus(@PathVariable("orderNumber") String orderNumber) {
		return orderService.updateOrderStatus(orderNumber);
	}

	@GetMapping("/getMessage/{message}")
	public String getMessage(@PathVariable("message") String message) {
		return "SUCCESS";
	}
}
