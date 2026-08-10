package com.ecommerce.engine.dto;

import java.util.List;

/**
 * Wrapper returned by AI endpoints so clients get both the LLM explanation
 * and structured product matches.
 */
public class AiResponse {

    /** Natural-language answer from ChatClient */
    private String aiMessage;

    /** Product IDs the AI referenced (parsed from its response when possible) */
    private List<Long> suggestedProductIds;

    public AiResponse() {
    }

    public AiResponse(String aiMessage, List<Long> suggestedProductIds) {
        this.aiMessage = aiMessage;
        this.suggestedProductIds = suggestedProductIds;
    }

    public String getAiMessage() {
        return aiMessage;
    }

    public void setAiMessage(String aiMessage) {
        this.aiMessage = aiMessage;
    }

    public List<Long> getSuggestedProductIds() {
        return suggestedProductIds;
    }

    public void setSuggestedProductIds(List<Long> suggestedProductIds) {
        this.suggestedProductIds = suggestedProductIds;
    }
}
