package com.ecommerce.engine.dto;

/**
 * Request body for personalized product recommendations.
 * The service loads the user's order history and asks the LLM to suggest items.
 */
public class AiRecommendationRequest {

    private Long userId;

    /** Optional hint, e.g. "gift for a runner" */
    private String preferenceHint;

    public AiRecommendationRequest() {
    }

    public AiRecommendationRequest(Long userId, String preferenceHint) {
        this.userId = userId;
        this.preferenceHint = preferenceHint;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPreferenceHint() {
        return preferenceHint;
    }

    public void setPreferenceHint(String preferenceHint) {
        this.preferenceHint = preferenceHint;
    }
}
