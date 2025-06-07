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
	private String jwt;
	private UserResp user;
	public RegisterResponse(String message, String jwt, UserResp user) {
		this.message = message;
		this.jwt = jwt;
		this.user = user;
		this.timeStamp=LocalDateTime.now();
	}
}
