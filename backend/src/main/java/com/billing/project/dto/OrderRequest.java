package com.billing.project.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.billing.project.entities.OrderItem;
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
    private List<OrderItemRequest> orderItems;
}
