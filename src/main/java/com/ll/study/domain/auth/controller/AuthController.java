package com.ll.study.domain.auth.controller;

import com.ll.study.domain.auth.controller.dto.LoginRequest;
import com.ll.study.domain.auth.controller.dto.LoginResponse;
import com.ll.study.domain.auth.service.AuthService;
import com.ll.study.domain.auth.util.AuthorizationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = null;
        try {
            response = authService.login(request);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String authHeader) {
        String accessToken = AuthorizationUtils.getAccessToken(authHeader);
        authService.logout(accessToken);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
