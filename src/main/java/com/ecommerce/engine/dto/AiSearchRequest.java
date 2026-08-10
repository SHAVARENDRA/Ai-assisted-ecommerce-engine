package com.ecommerce.engine.dto;

/**
 * Request body for AI-powered natural language product search.
 * Example: { "query": "Show me budget laptops under $800" }
 */
public class AiSearchRequest {

    private String query;

    public AiSearchRequest() {
    }

    public AiSearchRequest(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
