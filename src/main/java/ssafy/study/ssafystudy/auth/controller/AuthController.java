package ssafy.study.ssafystudy.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ssafy.study.ssafystudy.auth.controller.dto.LoginRequest;
import ssafy.study.ssafystudy.auth.controller.dto.LoginResponse;
import ssafy.study.ssafystudy.auth.service.AuthService;
import ssafy.study.ssafystudy.auth.util.AuthTokenUtils;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@RequestHeader("Authorization") String bearerToken) {
        if (AuthTokenUtils.isValidBearerToken(bearerToken)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다.");
        }

        String sessionKey = AuthTokenUtils.parseBearerToken(bearerToken);
        authService.logout(sessionKey);
    }
}