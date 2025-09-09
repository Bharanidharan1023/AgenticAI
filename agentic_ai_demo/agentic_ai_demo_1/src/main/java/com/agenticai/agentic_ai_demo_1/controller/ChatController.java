package com.agenticai.agentic_ai_demo_1.controller;

import com.agenticai.agentic_ai_demo_1.model.ChatModel;
import com.agenticai.agentic_ai_demo_1.service.OllamaChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final OllamaChatService ollamaChatService;

    @GetMapping("/chat")
    public ChatModel chat(@RequestBody ChatModel chatModel){
        return ollamaChatService.chat(chatModel);
    }

}
