package com.billing.project.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.billing.project.entities.User;
import com.billing.project.repos.UserRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {
	//depcy  - user dao
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user=userRepository.findByEmail(email)
				.orElseThrow(() -> 
				new UsernameNotFoundException("Invalid email ....."));
		//=> partial auth - email exists
		return user;
	}

}
