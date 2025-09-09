package com.example.rag.component;

import com.example.rag.service.RagService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final RagService ragService;

    public ChatController(RagService ragService) {
        this.ragService = ragService;
    }

    @GetMapping
    public String chat(@RequestBody String q) {
        return ragService.ask(q);
    }
}
