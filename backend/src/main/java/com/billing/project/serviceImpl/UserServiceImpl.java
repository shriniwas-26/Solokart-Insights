package com.billing.project.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.billing.project.custom_exception.ApiException;
import com.billing.project.custom_exception.ResourceNotFoundException;
import com.billing.project.dto.ApiResponse;
import com.billing.project.dto.RegisterRequest;
import com.billing.project.dto.RegisterResponse;
import com.billing.project.dto.UserResp;
import com.billing.project.entities.Role;
import com.billing.project.entities.User;
import com.billing.project.repos.UserRepository;
import com.billing.project.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;

	@Override
	public List<UserResp> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> allUsers = userRepository.getAllUsers(Role.USER);
		return allUsers.stream().map(entity -> modelMapper.map(entity, UserResp.class)).collect(Collectors.toList());
	}

	@Override
	public UserResp getUserById(Long id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("User not found with given id"));
		return modelMapper.map(user, UserResp.class);
	}

	@Override
	public RegisterResponse createUser(RegisterRequest userDto) {
		// TODO Auto-generated method stub
		if(userRepository.existsByEmail(userDto.getEmail())) {
	    	   throw new ApiException("duplicate email detected !!!");
	       }
		User user = modelMapper.map(userDto, User.class);
		user.setRole(Role.USER);
		userRepository.save(user);
		return new RegisterResponse("user registered successfully");
	}

	@Override
	public ApiResponse updateUser(Long id, RegisterRequest userRequest) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
		
		user.setFirstName(userRequest.getFirstName());
		user.setLastName(userRequest.getLastName());
		user.setEmail(userRequest.getEmail());
		user.setPassword(userRequest.getPassword());
		
		userRepository.save(user);
		return new ApiResponse("user updated successfully...");
	}

	@Override
	public ApiResponse deleteUser(Long id) {
		
		// TODO Auto-generated method stub
		User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
		
		userRepository.delete(user);
		return new ApiResponse("User deleted successfully...");
	}

}
