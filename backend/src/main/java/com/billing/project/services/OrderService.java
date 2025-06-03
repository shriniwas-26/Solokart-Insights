package com.billing.project.services;

import java.time.LocalDate;
import java.util.List;

import com.billing.project.entities.Order;

public interface OrderService {
	Order createOrder(Order order);
    List<Order> getUserOrders(Long userId);
    List<Order> getOrdersByDate(LocalDate date);
    Double getTotalSalesForDate(LocalDate date);
    Order getOrderById(Long id);
}
