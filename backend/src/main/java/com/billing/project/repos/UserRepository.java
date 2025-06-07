package com.billing.project.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.billing.project.entities.Role;
import com.billing.project.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
	
	boolean existsByEmail(String email);
	
	Optional<User> findByEmailAndPassword(String email, String Password);
	
	Optional<User> findById(Long id);
	
	@Query("select u from User u where u.role= :userRole")
	List<User> getAllUsers(@Param("userRole") Role userRole);
}
