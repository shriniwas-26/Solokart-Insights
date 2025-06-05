package com.billing.project.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.billing.project.entities.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusDto {
	private OrderStatus status;
}
