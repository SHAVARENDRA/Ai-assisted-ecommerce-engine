package com.ecommerce.engine.service;

import com.ecommerce.engine.dto.AiRecommendationRequest;
import com.ecommerce.engine.dto.AiResponse;
import com.ecommerce.engine.dto.AiSearchRequest;
import com.ecommerce.engine.model.Order;
import com.ecommerce.engine.model.Product;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Spring AI integration layer.
 * <p>
 * <b>How ChatClient works (interview talking points):</b>
 * <ol>
 *   <li>We load real product data from PostgreSQL via {@link ProductService}.</li>
 *   <li>We embed that catalog as text inside a user prompt.</li>
 *   <li>{@link ChatClient} sends the prompt to OpenAI and returns a natural-language reply.</li>
 *   <li>The LLM interprets queries like "affordable running shoes" against the catalog context.</li>
 * </ol>
 * This is <em>retrieval-augmented</em> style search: database fetch + LLM reasoning, not vector DB here.
 */
@Service
public class AiSearchService {

    private static final Pattern PRODUCT_ID_PATTERN = Pattern.compile("product\\s*#?(\\d+)", Pattern.CASE_INSENSITIVE);

    private final ChatClient chatClient;
    private final ProductService productService;
    private final OrderService orderService;

    public AiSearchService(ChatClient chatClient, ProductService productService, OrderService orderService) {
        this.chatClient = chatClient;
        this.productService = productService;
        this.orderService = orderService;
    }

    /**
     * Natural-language product search.
     * <p>
     * Endpoint flow: POST /api/ai/search → Controller → this method → ChatClient → OpenAI API.
     */
    @Transactional(readOnly = true)
    public AiResponse searchProducts(AiSearchRequest request) {
        if (request.getQuery() == null || request.getQuery().isBlank()) {
            throw new IllegalArgumentException("Search query must not be empty");
        }

        List<Product> catalog = productService.findAll();
        String catalogContext = formatCatalog(catalog);

        String aiMessage = chatClient.prompt()
                .user(user -> user.text("""
                        Customer search query: "{query}"

                        Available products (id | name | category | price | description):
                        {catalog}

                        Recommend the best matching products. Mention product IDs as "Product #123".
                        Explain briefly why each item fits the query.
                        """)
                        .param("query", request.getQuery())
                        .param("catalog", catalogContext))
                .call()
                .content();

        return new AiResponse(aiMessage, extractProductIds(aiMessage));
    }

    /**
     * Dynamic recommendations based on a user's purchase history.
     * <p>
     * Loads past orders with JOIN FETCH, summarizes preferences, and asks the LLM
     * to suggest complementary items from the current catalog.
     */
    @Transactional(readOnly = true)
    public AiResponse recommendProducts(AiRecommendationRequest request) {
        if (request.getUserId() == null) {
            throw new IllegalArgumentException("userId is required for recommendations");
        }

        List<Order> pastOrders = orderService.findByUserId(request.getUserId());
        List<Product> catalog = productService.findAll();

        String purchaseHistory = pastOrders.isEmpty()
                ? "No previous orders (new customer)."
                : pastOrders.stream()
                .map(o -> "- Bought: " + o.getProduct().getName()
                        + " (" + o.getProduct().getCategory() + "), qty " + o.getQuantity())
                .collect(Collectors.joining("\n"));

        String hint = request.getPreferenceHint() != null ? request.getPreferenceHint() : "none";

        String aiMessage = chatClient.prompt()
                .user(user -> user.text("""
                        User ID: {userId}
                        Purchase history:
                        {history}

                        Optional preference hint: {hint}

                        Product catalog:
                        {catalog}

                        Suggest 3-5 products this customer would like. Reference IDs as "Product #123".
                        Do not recommend items they already bought unless it's a replenishment item.
                        """)
                        .param("userId", request.getUserId())
                        .param("history", purchaseHistory)
                        .param("hint", hint)
                        .param("catalog", formatCatalog(catalog)))
                .call()
                .content();

        return new AiResponse(aiMessage, extractProductIds(aiMessage));
    }

    /** Converts entities to a compact text block the LLM can read. */
    private String formatCatalog(List<Product> products) {
        if (products.isEmpty()) {
            return "(empty catalog)";
        }
        return products.stream()
                .map(p -> String.format("#%d | %s | %s | $%s | %s",
                        p.getId(), p.getName(), p.getCategory(), p.getPrice(),
                        p.getDescription() != null ? p.getDescription() : ""))
                .collect(Collectors.joining("\n"));
    }

    /** Parses "Product #42" style references from the LLM response. */
    private List<Long> extractProductIds(String aiMessage) {
        List<Long> ids = new ArrayList<>();
        Matcher matcher = PRODUCT_ID_PATTERN.matcher(aiMessage);
        while (matcher.find()) {
            ids.add(Long.parseLong(matcher.group(1)));
        }
        return ids.stream().distinct().toList();
    }
}
