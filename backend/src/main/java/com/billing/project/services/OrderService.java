package com.billing.project.services;

import java.time.LocalDate;
import java.util.List;

import com.billing.project.dto.ApiResponse;
import com.billing.project.dto.OrderDto;
import com.billing.project.dto.OrderRequest;
import com.billing.project.dto.OrderStatusDto;
import com.billing.project.entities.Order;

public interface OrderService {
	List<OrderDto> getAll();
    OrderDto getById(Long id);
    List<OrderDto> getByUserId(Long userId);
    OrderDto create(OrderRequest orderDto);
    ApiResponse delete(Long id);
	OrderDto updateOrderStatus(Long id, OrderStatusDto orderStatus);
    
}
