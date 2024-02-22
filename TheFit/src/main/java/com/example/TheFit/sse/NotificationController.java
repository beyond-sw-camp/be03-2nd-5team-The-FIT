package com.example.TheFit.sse;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping(value = "/subscribe/{email}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@PathVariable String email) {
        System.out.println("heeer");
        return notificationService.subscribe(email);
    }

    @PostMapping("/send-data/{email}")
    public void sendData(@PathVariable String email) {
        System.out.println("heeer2");
        notificationService.notify(email, "data");
    }
}