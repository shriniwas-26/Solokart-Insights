package com.billing.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.billing.project.dto.OrderDto;
import com.billing.project.dto.OrderRequest;
import com.billing.project.dto.OrderStatusDto;
import com.billing.project.services.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getOrdersByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderDto) {
        return ResponseEntity.ok(orderService.create(orderDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateOrderStatus(
    		@PathVariable Long id,
    		@RequestBody OrderStatusDto orderStatus
    		){
    	return ResponseEntity.ok(orderService.updateOrderStatus(id,orderStatus));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.delete(id));
    }

}
