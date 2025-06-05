package com.billing.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
	private Long id;
	private Long orderId;
	private Long itemId;
	private Integer quantity;
	private Double priceAtOrderTime;
}
