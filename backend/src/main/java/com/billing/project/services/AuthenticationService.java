package com.billing.project.services;

import com.billing.project.dto.AuthenticationRequest;
import com.billing.project.dto.AuthenticationResponse;
import com.billing.project.dto.RegisterRequest;
import com.billing.project.dto.UserResp;

public interface AuthenticationService {
	UserResp register(RegisterRequest request);
	UserResp authenticate(AuthenticationRequest request);
}
