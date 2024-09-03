package com.login.login.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AutControlller {
    @Autowired
    private AutService autService;

    
    public AutControlller(AutService autService) {
        this.autService = autService;
    }

    @PostMapping("/login")
    public ResponseEntity<AutResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(autService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AutResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(autService.register(request));
    }


}
