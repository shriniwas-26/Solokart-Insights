package com.billing.project.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RegisterResponse {
	private LocalDateTime timeStamp;
	private String message;
	public RegisterResponse(String message) {
		this.message = message;
		this.timeStamp=LocalDateTime.now();
	}
}
