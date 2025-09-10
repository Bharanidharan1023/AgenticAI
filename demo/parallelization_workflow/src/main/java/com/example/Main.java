package com.example;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Main.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ChatClient.Builder chatClientBuilder) {

        return args -> {

            List<String> parallelResponse = new ParallelizationlWorkflow(chatClientBuilder.build())
                    .parallel("""
                                     Rewrite the input text in the following way:
                                                    1. Simplify for a 10-year-old.
                                                    2. Translate to Hindi.
                                                    3. Translate to French.
                                                    4. Make it sound funny.""",
                            List.of(
                                    "The future of artificial intelligence will change how humans work.",
                                    "Space exploration opens new opportunities for science and business.",
                                    "Climate change is the biggest challenge of our generation.",
                                    "Healthy eating improves focus, energy, and long-term well-being."
                                   ),
                            4);

            System.out.println(parallelResponse);

        };
    }
}