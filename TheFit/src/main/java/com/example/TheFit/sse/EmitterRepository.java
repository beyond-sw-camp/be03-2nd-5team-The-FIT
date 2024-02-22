package com.example.TheFit.sse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
public class EmitterRepository {
    // 모든 Emitters를 저장하는 ConcurrentHashMap
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    /**
     * 주어진 아이디와 이미터를 저장
     *
     * @param email      - 사용자 아이디.
     * @param emitter - 이벤트 Emitter.
     */
    public void save(String email, SseEmitter emitter) {
        emitters.put(email, emitter);
    }

    /**
     * 주어진 아이디의 Emitter를 제거
     *
     * @param email - 사용자 아이디.
     */
    public void deleteByEmail(String email) {
        emitters.remove(email);
    }

    /**
     * 주어진 아이디의 Emitter를 가져옴.
     *
     * @param email - 사용자 아이디.
     * @return SseEmitter - 이벤트 Emitter.
     */
    public SseEmitter get(String email) {
        return emitters.get(email);
    }
}