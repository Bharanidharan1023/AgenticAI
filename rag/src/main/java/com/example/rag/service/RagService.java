package com.example.rag.service;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RagService {

    private static final Logger log = LoggerFactory.getLogger(RagService.class);
    private static final String KNOWLEDGE_BASE_FILE = "classpath:knowledgebase.txt";

    private final ChatClient chatClient;
    private final ResourceLoader resourceLoader;
    private final List<String> knowledgeBase = new ArrayList<>();

    public RagService(ChatClient chatClient, ResourceLoader resourceLoader) {
        this.chatClient = chatClient;
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    public void init() {
        log.info("Loading knowledge base from: {}", KNOWLEDGE_BASE_FILE);
        Resource resource = resourceLoader.getResource(KNOWLEDGE_BASE_FILE);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            // Read all lines from the file and add them to our in-memory list
            knowledgeBase.addAll(reader.lines().toList());
            log.info("Knowledge base loaded successfully with {} entries.", knowledgeBase.size());
        } catch (IOException e) {
            throw new RuntimeException("Failed to load knowledge base file", e);
        }
    }

    public String ask(String question) {
        // More dynamic keyword-based retrieval from the question itself
        String[] keywords = question.toLowerCase().split("\\s+");

        // Find all facts in the knowledge base that contain any of the keywords
        String context = knowledgeBase.stream()
                .filter(fact -> Arrays.stream(keywords).anyMatch(fact.toLowerCase()::contains))
                .collect(Collectors.joining("\n"));

        if (context.isEmpty()) {
            context = "No relevant knowledge found in the knowledge base.";
        }

        log.info("Building prompt with context: {}", context);

        // Pass context + question to LLM
        return chatClient.prompt()
                .user("Use this context:\n" + context + "\n\nQuestion: " + question)
                .call()
                .content();
    }
}
