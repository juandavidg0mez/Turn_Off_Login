package com.login.login.Auth;

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
    private AutService autService;
    @PostMapping("/login")
    public ResponseEntity<AutResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(autService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AutResponse> register(@RequestBody RequisterRequest request) {
        return ResponseEntity.ok(autService.register(request));
    }


}
