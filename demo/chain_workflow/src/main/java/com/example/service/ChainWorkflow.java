package com.example.service;

import groovy.util.logging.Slf4j;
import org.springframework.ai.chat.client.ChatClient;

@lombok.extern.slf4j.Slf4j
@Slf4j
public class ChainWorkflow {

    private static final String[] DEFAULT_SYSTEM_PROMPTS = {

            // Step 1: Translate to French
            """
            Translate the following text into French.
            """,

                    // Step 2: Convert translation into uppercase
                    """
            Convert the following text into uppercase.
            """,

                    // Step 3: Make it sound poetic
                    """
            Rewrite the following text in a poetic style.
            """,

                    // Step 4: Give a motivational ending
                    """
            Add a short motivational ending to the text.
            """
    };

    private final ChatClient chatClient;

    private final String[] systemPrompts;

    public ChainWorkflow(ChatClient chatClient) {
        this(chatClient, DEFAULT_SYSTEM_PROMPTS);
    }

    public ChainWorkflow(ChatClient chatClient, String[] systemPrompts) {
        this.chatClient = chatClient;
        this.systemPrompts = systemPrompts;
    }

    public String chain(String userInput) {

        int step = 0;
        String response = userInput;
        ChainWorkflow.log.info("STEP {}:\n {}", step++, response);

        for (String prompt : systemPrompts) {

            // 1. Compose the input using the response from the previous step.
            String input = String.format("{%s}\n {%s}", prompt, response);

            // 2. Call the chat client with the new input and get the new response.
            response = chatClient.prompt(input).call().content();

            log.info(String.format("\nSTEP %s:\n %s", step++, response));
        }

        return response;
    }
}
