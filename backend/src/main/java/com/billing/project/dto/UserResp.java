package com.billing.project.dto;

import java.time.LocalDateTime;

import com.billing.project.entities.Role;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserResp {
    private Long id;
    
	
	private String firstName;
	
	
	private String lastName;
	
    
    private String email;
    
    
    private Role role;
    
    
    private LocalDateTime createdAt;
}
