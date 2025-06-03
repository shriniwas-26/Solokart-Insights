package com.billing.project.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.billing.project.custom_exception.ApiException;
import com.billing.project.custom_exception.AuthenticationException;
import com.billing.project.dto.AuthenticationRequest;
import com.billing.project.dto.RegisterRequest;
import com.billing.project.dto.UserResp;
import com.billing.project.entities.User;
import com.billing.project.repos.UserRepository;
import com.billing.project.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
   
//    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    @Override
    public UserResp register(RegisterRequest request) {
       if(userRepository.existsByEmail(request.getEmail())) {
    	   throw new ApiException("duplicate email detected !!!");
       }
       
       User userEntity = modelMapper.map(request, User.class);
       
       
//        passwordEncoder.encode(request.getPassword())
        return modelMapper.map(userRepository.save(userEntity), UserResp.class);
    }

	@Override
	public UserResp authenticate(AuthenticationRequest request) {
		// TODO Auto-generated method stub
		User entity = userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword())
				.orElseThrow(()-> new AuthenticationException("Invalid email or password"));
		return modelMapper.map(entity, UserResp.class);
	}

//    @Override
//    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getUsername(),
//                        request.getPassword()
//                )
//        );
//        var user = userRepository.findByUsername(request.getUsername())
//                .orElseThrow();
//        var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .role(user.getRole().name())
//                .build();
//    }
}
