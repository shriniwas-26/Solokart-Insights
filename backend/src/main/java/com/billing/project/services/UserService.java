package com.billing.project.services;

import java.util.List;

import com.billing.project.entities.User;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);

}
