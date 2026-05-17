package ssafy.study.ssafystudy.auth.component;

import org.springframework.stereotype.Component;
import ssafy.study.ssafystudy.global.exception.CustomException;
import ssafy.study.ssafystudy.global.exception.error.ErrorCode;

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
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
        return memberId;
    }
}
