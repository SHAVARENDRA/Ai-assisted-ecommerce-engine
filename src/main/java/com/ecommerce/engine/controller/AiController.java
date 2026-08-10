package com.ecommerce.engine.controller;

import com.ecommerce.engine.dto.AiRecommendationRequest;
import com.ecommerce.engine.dto.AiResponse;
import com.ecommerce.engine.dto.AiSearchRequest;
import com.ecommerce.engine.service.AiSearchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AI-powered endpoints using Spring AI {@code ChatClient}.
 * <p>
 * <b>Interview explanation:</b>
 * <ul>
 *   <li>{@code POST /api/ai/search} — customer types natural language; we fetch the DB catalog,
 *       send both to the LLM, and return an explanation plus suggested product IDs.</li>
 *   <li>{@code POST /api/ai/recommend} — we JOIN user order history, pass it as context,
 *       and the LLM suggests personalized items.</li>
 * </ul>
 * Requires {@code SPRING_AI_OPENAI_API_KEY} environment variable at runtime.
 */
@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiSearchService aiSearchService;

    public AiController(AiSearchService aiSearchService) {
        this.aiSearchService = aiSearchService;

    }

    /**
     * Natural language product search.
     * Example body: { "query": "wireless earbuds under 50 dollars" }
     */
    @PostMapping("/search")
    public AiResponse search(@RequestBody AiSearchRequest request) {
        return aiSearchService.searchProducts(request);
    }

    /**
     * Personalized recommendations from purchase history.
     * Example body: { "userId": 1, "preferenceHint": "birthday gift for a gamer" }
     */
    @PostMapping("/recommend")
    public AiResponse recommend(@RequestBody AiRecommendationRequest request) {
        return aiSearchService.recommendProducts(request);
    }
}
