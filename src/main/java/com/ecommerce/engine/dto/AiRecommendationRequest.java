package com.ecommerce.engine.dto;


public class AiRecommendationRequest {

    private Long userId;


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
