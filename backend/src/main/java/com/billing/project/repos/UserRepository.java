package com.billing.project.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.billing.project.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}
