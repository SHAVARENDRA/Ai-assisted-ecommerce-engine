package com.ecommerce.engine.dto;

import java.util.List;


public class AiResponse {


    private String aiMessage;


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
