package com.agenticai.agentic_ai_demo_1.service;

import com.agenticai.agentic_ai_demo_1.model.ChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OllamaChatService {
    
    private ChatClient chatClient;

    public OllamaChatService(ChatClient.Builder builder,  ChatMemory chatMemory, List<ToolCallback> tools) {
        chatClient = builder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultToolCallbacks(tools)
                .build();
    }

    public ChatModel chat(ChatModel message){
        ChatModel chatModel = new ChatModel();
        String response = chatClient.prompt(message.getMessage()).call().content();
        chatModel.setMessage(response);
        return chatModel;
    }
}
