package com.thomasleconte.langchain_test.service;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.mistralai.MistralAiEmbeddingModel;
import dev.langchain4j.rag.content.Content;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.query.Query;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.chroma.ChromaEmbeddingStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentRetrieveService implements ContentRetriever {

    private final EmbeddingStore<TextSegment> chromaVectorStore;
    private final MistralAiEmbeddingModel mistralAiEmbeddingModel;

    @Override
    public List<Content> retrieve(Query query) {
        Embedding queryEmbedding = mistralAiEmbeddingModel.embed(TextSegment.textSegment(query.text())).content();
        return chromaVectorStore.search(EmbeddingSearchRequest.builder()
                .queryEmbedding(queryEmbedding)
                .minScore(0.85)
                .build())
                .matches().stream()
                .map(e -> Content.from(e.embedded().text()))
                .toList();
    }
}
