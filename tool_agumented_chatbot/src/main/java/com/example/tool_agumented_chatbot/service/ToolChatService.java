package com.example.tool_agumented_chatbot.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ToolChatService {

    private final ChatClient chatClient;

    // The ChatClient.Builder is configured by Spring Boot to know about all
    // Function<I,O> beans in the application context.
    public ToolChatService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String chat(String userMessage) {
        // The .function() call makes the tool available to the LLM for this specific request.
        return chatClient.prompt()
                .user(userMessage)
                .call()
                .content();
    }
}