package com.thomasleconte.langchain_test.service;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.mistralai.MistralAiChatModel;
import dev.langchain4j.rag.content.Content;
import dev.langchain4j.rag.query.Query;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final DocumentRetrieveService documentRetrieveService;
    private final MistralAiChatModel mistralAiChatModel;
    private final ChatMemory chatMemory;

    public String chat(String message) {
        List<Content> docsResult = documentRetrieveService.retrieve(Query.from(message));
        String documentsFormatted = docsResult.stream()
                .map(t -> t.textSegment().text())
                .reduce("", (a, b) -> a + "\n" + b);

        String prompt = "Documents found: " + documentsFormatted + "\n\nUser query:" + message;

        chatMemory.add(UserMessage.from(prompt));

        ChatResponse result = mistralAiChatModel.chat(chatMemory.messages());

        chatMemory.add(result.aiMessage());

        return result.aiMessage().text();
    }

}
