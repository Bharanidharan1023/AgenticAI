package com.example;

import com.example.service.ChainWorkflow;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);}

        String msg = """
			The sun is shining and I feel happy today.
			""";

        @Bean
        public CommandLineRunner commandLineRunner(ChatClient.Builder chatClientBuilder) {
            return args -> {
                new ChainWorkflow(chatClientBuilder.build()).chain(msg);
            };
    }
}