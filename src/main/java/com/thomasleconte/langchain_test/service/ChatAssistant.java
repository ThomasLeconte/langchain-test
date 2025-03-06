package com.thomasleconte.langchain_test.service;

import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(contentRetriever = "documentRetrieveService", chatModel = "mistralAiChatModel", chatMemory = "chatMemory", wiringMode = AiServiceWiringMode.EXPLICIT)
public interface ChatAssistant {
    String chat(String userMessage);
}
