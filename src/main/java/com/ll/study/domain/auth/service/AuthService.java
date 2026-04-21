package com.ll.study.domain.auth.service;

import com.ll.study.domain.auth.component.SessionManager;
import com.ll.study.domain.auth.controller.dto.LoginRequest;
import com.ll.study.domain.auth.controller.dto.LoginResponse;
import com.ll.study.domain.member.entity.MemberEntity;
import com.ll.study.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final SessionManager sessionManager;
    private final MemberRepository memberRepository;

    public LoginResponse login(LoginRequest request) {
        MemberEntity member = memberRepository.findByLoginId(request.loginId())
                .orElseThrow(() -> new RuntimeException("아이디가 올바르지 않습니다."));

        if(member.isVaildPassword(request.password())) {
            String token = sessionManager.createSession(member.getId());
            return new LoginResponse(token, "Bearer");
        }
        throw new RuntimeException("비밀번호가 올바르지 않습니다.");
    }

    public void logout(String accessToken) {
        sessionManager.removeSession(accessToken);
    }

    public Long getMemberId(String accessToken) {
        return sessionManager.getMemberId(accessToken).orElseThrow(() -> new RuntimeException("아이디값 조회 불가"));
    }
}
