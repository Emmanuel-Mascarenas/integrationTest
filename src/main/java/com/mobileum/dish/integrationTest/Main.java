package com.mobileum.dish.integrationTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

    public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree("{}");

        root.withObject("")
            .put("leadId", "test-emas-20240107")
            .put("customerId", "777000{{TEST_ID_TYPE_2}}")
            .put("type", "IsFraud")
            .put("callContext", "NEW-SUB")
            .put("timestamp", "2023-10-02 00:00:00")
            .put("orderId", "7686-845-974")
            .put("marketingBrand", "BOOSTINFINITE")
            .put("billingType", "PML")
            .put("leadId", "test-emas-202401072")
;


        String json = mapper.writer().withDefaultPrettyPrinter().writeValueAsString(root);
        System.out.println(json);

    }

}
