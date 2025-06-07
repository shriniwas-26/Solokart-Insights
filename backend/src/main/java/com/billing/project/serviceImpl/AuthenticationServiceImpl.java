package com.billing.project.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.billing.project.config.JwtUtils;
import com.billing.project.custom_exception.ApiException;
import com.billing.project.custom_exception.AuthenticationException;
import com.billing.project.custom_exception.ResourceNotFoundException;
import com.billing.project.dto.ApiResponse;
import com.billing.project.dto.AuthenticationRequest;
import com.billing.project.dto.RegisterRequest;
import com.billing.project.dto.RegisterResponse;
import com.billing.project.dto.UserResp;
import com.billing.project.entities.Role;
import com.billing.project.entities.User;
import com.billing.project.repos.UserRepository;
import com.billing.project.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final ModelMapper modelMapper;
   

	@Override
	public RegisterResponse authenticate(AuthenticationRequest request) {
		// TODO Auto-generated method stub

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.getEmail(),
	    		   request.getPassword());
			System.out.println("is authenticated " + authToken.isAuthenticated());// f
			// 2. invoke AuthenticationManager's - authenticate method - spring sec supplied
			Authentication successfulAuth = authenticationManager.authenticate(authToken);
			// in case of failure - throws AuthenticationException
			// in case of success- rets user details object - within auth
			// 3. => success
			System.out.println("is authenticated " + successfulAuth.isAuthenticated());// true
			System.out.println("principal " + successfulAuth.getPrincipal());// user details + granted authorities
			System.out.println("principal class" + successfulAuth.getPrincipal().getClass());// 
			
			User user = userRepository.findByEmail(request.getEmail())
					.orElseThrow(()-> new ResourceNotFoundException("User not found with given email id...."));
		
		return new RegisterResponse("User logged in successfully...", jwtUtils.generateJwtToken(successfulAuth), modelMapper.map(user, UserResp.class));
	}


	@Override
	public UserResp registerAdmin(RegisterRequest registerDto) {
		// TODO Auto-generated method stub
		if(userRepository.existsByEmail(registerDto.getEmail())) {
	    	   throw new ApiException("duplicate email detected !!!");
	       }
		
		
		User user = modelMapper.map(registerDto, User.class);
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole(Role.ROLE_ADMIN);
		
		return modelMapper.map(userRepository.save(user), UserResp.class);
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
