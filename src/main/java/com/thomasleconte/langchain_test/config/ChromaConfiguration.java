package com.thomasleconte.langchain_test.config;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.chroma.ChromaEmbeddingStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChromaConfiguration {

    @Value("${chroma.base-url}")
    private String baseUrl;

    @Value("${chroma.collection-name}")
    private String collectionName;

    @Bean(name = "chromaVectorStore")
    public EmbeddingStore<TextSegment> chromaVectorStore() {
        return ChromaEmbeddingStore.builder()
                .baseUrl(baseUrl)
                .collectionName(collectionName)
                .build();
    }
}
