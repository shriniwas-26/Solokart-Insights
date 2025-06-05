package com.billing.project.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.billing.project.entities.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
	private Long userId;
    private Double totalAmount;
    private OrderStatus status;
    private List<OrderItemDto> orderItems;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
