package dev.sandrocaseiro.springbootitExample.states;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;
import static io.restassured.RestAssured.given;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class TesteState {
    private static final String REQUEST = "request";
    private static final String RESPONSE = "response";
    private static final String AUTH = "auth";
    private static final String REQUEST_PAYLOAD = "request_payload";
    private static final String USER = "user";

    private final Map<String, Object> states = new HashMap<>();

    private <T> T get(String name) {
        return (T) states.getOrDefault(name, null);
    }

    private <T> T set(String name, T object) {
        states.put(name, object);

        return object;
    }

    public RequestSpecification getRequest() {
        RequestSpecification request = get(REQUEST);
        if (request == null)
            request = setRequest(given()
                .contentType(ContentType.JSON)
            );

        return request;
    }

    public RequestSpecification setRequest(RequestSpecification request) {
        return set(REQUEST, request);
    }

    public Response getResponse() {
        return get(RESPONSE);
    }

    public void setResponse(Response response) {
        set(RESPONSE, response);
    }

    public void setAuth(String auth) {
        getRequest()
            .header(HttpHeaders.AUTHORIZATION, auth);
    }

    public JsonNode getRequestPayload() {
        return get(REQUEST_PAYLOAD);
    }

    public void setRequestPayload(String payload) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        set(REQUEST_PAYLOAD, mapper.readTree(payload));
    }
}
