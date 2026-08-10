package com.ecommerce.engine.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring AI configuration.
 * <p>
 * {@link ChatClient} is the high-level API for sending prompts to an LLM (OpenAI).
 * Spring Boot auto-configures the underlying model when {@code spring-ai-starter-model-openai}
 * is on the classpath and {@code spring.ai.openai.api-key} is set.
 */
@Configuration
public class AiConfig {

    /**
     * Builds a {@link ChatClient} with a default system prompt for e-commerce tasks.
     * Services inject this bean to run natural-language search and recommendations.
     */
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
