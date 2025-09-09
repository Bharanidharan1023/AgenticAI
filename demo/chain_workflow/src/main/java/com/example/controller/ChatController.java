package com.example.controller;

import com.example.service.OllamaChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final OllamaChatService ollamaChatService;

    @GetMapping("/chat")
    public String chat(@RequestBody String chatModel){
        return ollamaChatService.chat(chatModel);
    }

}
