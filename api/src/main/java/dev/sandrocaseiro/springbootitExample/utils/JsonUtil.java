package dev.sandrocaseiro.springbootitExample.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.sandrocaseiro.springbootitExample.configs.WebConfig;
import feign.Response;

import java.io.IOException;

public final class JsonUtil {
    private static final ObjectMapper mapper = WebConfig.getJsonMapper();

    private JsonUtil() { }

    public static <T> T deserialize(JsonNode node, Class<T> clazz) {
        if (node == null)
            return null;

        return mapper.convertValue(node, clazz);
    }

    public static JsonNode deserializeTree(Response.Body body) {
        if (body == null)
            return null;

        try {
            return mapper.readTree(body.asInputStream());
        }
        catch (IOException e) {
            return null;
        }
    }
}
