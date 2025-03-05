package com.thomasleconte.langchain_test.config;

import dev.langchain4j.model.mistralai.MistralAiChatModel;
import dev.langchain4j.model.mistralai.MistralAiChatModelName;
import dev.langchain4j.model.mistralai.MistralAiEmbeddingModel;
import dev.langchain4j.model.mistralai.MistralAiEmbeddingModelName;
import dev.langchain4j.model.mistralai.internal.client.DefaultMistralAiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MistralModelsConfiguration {

    @Value("${mistralai.api-key}")
    private String apiKey;

    @Bean(name = "mistralAiChatModel")
    public MistralAiChatModel mistralAiChatModel() {
        return MistralAiChatModel.builder()
                .apiKey(apiKey)
                .modelName(MistralAiChatModelName.OPEN_MIXTRAL_8X22B)
                .build();
    }

    @Bean(name = "mistralAiSmallChatModel")
    public MistralAiChatModel mistralAiSmallChatModel() {
        return MistralAiChatModel.builder()
                .apiKey(apiKey)
                .modelName(MistralAiChatModelName.OPEN_MISTRAL_7B)
                .logRequests(true)
                .logResponses(true)
                .build();
    }

    @Bean(name = "mistralAiLargeChatModel")
    public MistralAiChatModel mistralAiLargeChatModel() {
        return MistralAiChatModel.builder()
                .apiKey(apiKey)
                .modelName(MistralAiChatModelName.MISTRAL_LARGE_LATEST)
                .build();
    }

    @Bean(name = "mistralAiEmbeddingModel")
    public MistralAiEmbeddingModel mistralAiEmbeddingModel() {
        return MistralAiEmbeddingModel.builder()
                .apiKey(apiKey)
                .modelName(MistralAiEmbeddingModelName.MISTRAL_EMBED)
                .build();
    }
}
