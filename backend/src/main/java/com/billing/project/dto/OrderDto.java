package com.billing.project.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.billing.project.entities.OrderItem;
import com.billing.project.entities.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
	private Long id;
    private UserResp user;
    private Double totalAmount;
    private OrderStatus status;
    private List<OrderItem> orderItems;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
