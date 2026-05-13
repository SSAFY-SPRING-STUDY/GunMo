package ssafy.study.ssafystudy.auth.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ssafy.study.ssafystudy.auth.component.SessionManager;
import ssafy.study.ssafystudy.auth.controller.dto.LoginRequest;
import ssafy.study.ssafystudy.auth.controller.dto.LoginResponse;
import ssafy.study.ssafystudy.member.entity.MemberEntity;
import ssafy.study.ssafystudy.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    public LoginResponse login(LoginRequest request) {
        MemberEntity member = memberRepository.findByUsername(request.username())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "존재하지 않는 회원입니다."));

        if(!member.checkPassword(request.password())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        String sessionKey = sessionManager.createSession(member.getId());
        return LoginResponse.from(sessionKey);
    }
    public void logout(String sessionKey) {
        sessionManager.getMemberId(sessionKey);
        sessionManager.removeSession(sessionKey);
    }
}
