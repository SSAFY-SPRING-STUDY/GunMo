package com.ll.study.domain.auth.component;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {
    private final Map<String,Long> sessionsStore = new ConcurrentHashMap<>();

    public String createSession(Long id) {
        String token = UUID.randomUUID().toString();
        sessionsStore.put(token, id);
        return token;
    }

    public void removeSession(String accessToken) {
        sessionsStore.remove(accessToken);
    }

    public Optional<Long> getMemberId(String accessToken) {
        return Optional.ofNullable(sessionsStore.get(accessToken));
    }
}
