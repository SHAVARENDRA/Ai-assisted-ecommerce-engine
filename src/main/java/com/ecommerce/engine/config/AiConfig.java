package com.ecommerce.engine.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AiConfig {


    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem("""
                        You are an AI shopping assistant for an e-commerce platform.
                        Answer using only the product catalog data provided in each request.
                        Return concise, helpful responses and reference product IDs when recommending items.
                        """)
                .build();
    }
}
