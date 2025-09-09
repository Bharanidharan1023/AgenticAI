package com.example.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OllamaChatService {

    private final ChatClient chatClient;

    public OllamaChatService(ChatClient.Builder builder, ChatMemory chatMemory) {
        chatClient = builder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }

    public String chat(String message){
        return chatClient.prompt(message).call().content();
    }
}
