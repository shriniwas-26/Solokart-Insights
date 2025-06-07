package com.billing.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.billing.project.dto.AuthenticationRequest;
import com.billing.project.dto.AuthenticationResponse;
import com.billing.project.dto.RegisterRequest;
import com.billing.project.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/admin/register")
    public ResponseEntity<?> registerAdmin(
    			@RequestBody RegisterRequest registerDto
    		){
    	return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.registerAdmin(registerDto)); 
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}