package com.billing.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.billing.project.dto.RegisterRequest;
import com.billing.project.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	
	@GetMapping
	public ResponseEntity<?> getAllUsers(){
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id){
		return ResponseEntity.ok(userService.getUserById(id));
	}
	
	@PostMapping
    public ResponseEntity<?> createUser(@RequestBody RegisterRequest user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
            @RequestBody RegisterRequest userDetails
    ) {
        return ResponseEntity.ok(userService.updateUser(id, userDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
