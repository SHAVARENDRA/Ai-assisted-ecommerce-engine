package com.ecommerce.engine.controller;

import com.ecommerce.engine.dto.AiRecommendationRequest;
import com.ecommerce.engine.dto.AiResponse;
import com.ecommerce.engine.dto.AiSearchRequest;
import com.ecommerce.engine.service.AiSearchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiSearchService aiSearchService;

    public AiController(AiSearchService aiSearchService) {
        this.aiSearchService = aiSearchService;

    }


    @PostMapping("/search")
    public AiResponse search(@RequestBody AiSearchRequest request) {
        return aiSearchService.searchProducts(request);
    }


    @PostMapping("/recommend")
    public AiResponse recommend(@RequestBody AiRecommendationRequest request) {
        return aiSearchService.recommendProducts(request);
    }
}
