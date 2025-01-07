package com.mobileum.dish.integrationTest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ContextConfiguration(classes = AppConfig.class)
public class PocTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @BeforeEach
    public void callToGreetShouldReturnHello() {
        jdbcTemplate.execute("select current_timestamp");
    }

    @Test
    public void test2() throws IOException, InterruptedException {
        String url = "https://daorastfms03.dish.com:50001/rest_public/pos/IsFraudRequest_V2";

        HttpClient client = HttpClient.newHttpClient();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode requestRoot = mapper.readTree("{}");
        requestRoot.withObject("")
            .put("leadId", "test-emas-20240107")
            .put("customerId", "77700020240107")
            .put("type", "IsFraud")
            .put("callContext", "NEW-SUB")
            .put("timestamp", "2023-10-02 00:00:00")
            .put("orderId", "7686-845-974")
            .put("marketingBrand", "BOOSTINFINITE")
            .put("billingType", "PML");
        String requestJson = mapper.writer().withDefaultPrettyPrinter().writeValueAsString(requestRoot);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .POST(HttpRequest.BodyPublishers.ofString(requestJson))
            .setHeader("X-Client-Id", "WEB")
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        JsonNode resposeRoot = mapper.readTree(response.body());
        String resposeJson = mapper.writer().withDefaultPrettyPrinter().writeValueAsString(resposeRoot);

        System.out.println(resposeJson);
        System.out.println("message = " + resposeRoot.at("/error/code").asText());
        System.out.println("property = " + System.getProperty("someTestPoperty"));

    }

}
