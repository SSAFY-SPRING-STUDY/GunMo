package ssafy.study.ssafystudy.auth.component;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {
    private final ConcurrentHashMap<String, Long> sessions = new ConcurrentHashMap<>();

    public String createSession(Long memberId){
        String sessionKey = UUID.randomUUID().toString();
        sessions.put(sessionKey, memberId);

        return sessionKey;
    }

    public void removeSession(String sessionKey){
        sessions.remove(sessionKey);
    }

    public Long getMemberId(String sessionKey){
        Long memberId = sessions.get(sessionKey);
        if(memberId == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "세션이 유효하지 않습니다.");
        }
        return memberId;
    }
}
