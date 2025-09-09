package com.example;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ChatClient.Builder chatClientBuilder) {

        return args -> {

            List<String> parallelResponse = new ParallelizationlWorkflow(chatClientBuilder.build())
                    .parallel("""
							Analyze how market changes will impact this stakeholder group.
							Provide specific impacts and recommended actions.
							Format with clear sections and priorities.
							""",
                            List.of(
                                    """
                                            Customers:
                                            - Price sensitive
                                            - Want better tech
                                            - Environmental concerns
                                            """,

                                    """
                                            Employees:
                                            - Job security worries
                                            - Need new skills
                                            - Want clear direction
                                            """,

                                    """
                                            Investors:
                                            - Expect growth
                                            - Want cost control
                                            - Risk concerns
                                            """,

                                    """
                                            Suppliers:
                                            - Capacity constraints
                                            - Price pressures
                                            - Tech transitions
                                            """),
                            4);

            System.out.println(parallelResponse);

        };
    }
}