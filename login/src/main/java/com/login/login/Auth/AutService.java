package com.login.login.Auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.login.login.JTW.JwtService;
import com.login.login.User.Rol;
import com.login.login.User.User;
import com.login.login.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutService {

    private final UserRepository userRepository;
    private final JwtService jwtservice;
    private final PasswordEncoder passwordEncoder;

    public AutResponse login(LoginRequest request){
        return null;
    }
    public AutResponse register(RequisterRequest request){
        User user = User.builder()
        .username(request.getUsename())
        .password(request.getPassword())
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .country(request.getCountry())
        .role(Rol.USER)
        .build();
        
        userRepository.save(user);

        return AutResponse.builder()
            .token(jwtservice.getToken(user))
            .build();

    }
}
