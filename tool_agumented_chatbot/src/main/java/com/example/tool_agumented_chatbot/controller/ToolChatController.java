package com.example.tool_agumented_chatbot.controller;

import com.example.tool_agumented_chatbot.service.ToolChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToolChatController {

    private final ToolChatService toolChatService;

    public ToolChatController(ToolChatService toolChatService) {
        this.toolChatService = toolChatService;
    }

    @GetMapping("/chat/tools")
    public String chatWithTools(@RequestParam(defaultValue = "What is the weather in Chennai?") String message) {
        return toolChatService.chat(message);
    }
}