package com.billing.project.services;

import com.billing.project.dto.ApiResponse;
import com.billing.project.dto.AuthenticationRequest;
import com.billing.project.dto.AuthenticationResponse;
import com.billing.project.dto.RegisterRequest;
import com.billing.project.dto.RegisterResponse;
import com.billing.project.dto.UserResp;

public interface AuthenticationService {
	RegisterResponse authenticate(AuthenticationRequest request);

	UserResp registerAdmin(RegisterRequest registerDto);
}
