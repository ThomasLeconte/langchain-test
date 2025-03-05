package com.thomasleconte.langchain_test.controller;

import com.thomasleconte.langchain_test.service.ChatAssistant;
import com.thomasleconte.langchain_test.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final ChatAssistant chatAssistant;

    @GetMapping("/api/v1/chat")
    public String chatv1(@RequestParam String message) {
        log.info("Message : {}", message);
        return chatService.chat(message);
    }

    @GetMapping("/api/v2/chat")
    public String chatv2(@RequestParam String message) {
        log.info("Message : {}", message);
        return chatAssistant.chat(message);
    }
}
