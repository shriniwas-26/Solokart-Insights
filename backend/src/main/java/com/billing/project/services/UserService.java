package com.billing.project.services;

import java.util.List;

import com.billing.project.dto.ApiResponse;
import com.billing.project.dto.RegisterRequest;
import com.billing.project.dto.RegisterResponse;
import com.billing.project.dto.UserResp;
import com.billing.project.entities.User;

public interface UserService {
    List<UserResp> getAllUsers();
    UserResp getUserById(Long id);
    UserResp createUser(RegisterRequest userDto);
    ApiResponse updateUser(Long id, RegisterRequest user);
    ApiResponse deleteUser(Long id);

}
