package com.ecommerce.engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the AI-Assisted E-Commerce Engine.
 * <p>
 * {@code @SpringBootApplication} enables:
 * <ul>
 *   <li>Component scanning (Controllers, Services, Repositories)</li>
 *   <li>Auto-configuration (Web, JPA, Spring AI ChatClient)</li>
 *   <li>Embedded Tomcat server on port 8080</li>
 * </ul>
 */
@SpringBootApplication
public class AiEcommerceEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiEcommerceEngineApplication.class, args);
    }
}
