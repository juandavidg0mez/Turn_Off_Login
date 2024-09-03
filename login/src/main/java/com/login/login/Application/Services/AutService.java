package com.login.login.Application.Services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.login.login.Domain.AutResponse;
import com.login.login.Domain.LoginRequest;
import com.login.login.Domain.RegisterRequest;
import com.login.login.Domain.Rol;
import com.login.login.Domain.User;
import com.login.login.Infrastructure.Repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutService {

    private final UserRepository userRepository;
    private final JwtService jwtservice;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AutResponse login(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()) );
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtservice.getToken(user);
        return AutResponse.builder()
            .token(token)
            .build();

    }
    public AutResponse register(RegisterRequest request){
        User user = User.builder()
        .username(request.getUsername())
        .password(passwordEncoder.encode( request.getPassword()))
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .country(request.getCountry())
        .role(Rol.USER)
        .build();

    userRepository.save(user);

        
        userRepository.save(user);

        return AutResponse.builder()
            .token(jwtservice.getToken(user))
            .build();

    }
}
