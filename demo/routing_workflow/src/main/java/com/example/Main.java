package com.example;

import com.example.service.RoutingWorkflow;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }
    @Bean
    public CommandLineRunner commandLineRunner(ChatClient.Builder chatClientBuilder) {

        return args -> {
            Map<String, String> supportRoutes = Map.of(
                    "billing", "You are a billing support agent. Handle billing issues politely.",
                    "technical", "You are a technical support agent. Explain fixes step by step."
            );


            List<String> tickets = List.of(
                    "I was charged twice this month. Please fix it!",
                    "My app crashes every time I try to upload a file."
            );


            var routerWorkflow = new RoutingWorkflow(chatClientBuilder.build());

            int i = 1;
            for (String ticket : tickets) {
                System.out.println("\nTicket " + i++);
                System.out.println("------------------------------------------------------------");
                System.out.println(ticket);
                System.out.println("------------------------------------------------------------");
                System.out.println(routerWorkflow.route(ticket, supportRoutes));
            }

        };
    }
}