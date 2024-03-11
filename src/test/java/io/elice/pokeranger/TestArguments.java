package io.elice.pokeranger;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TestArguments {
    private final String urlTemplate;
    private final HttpMethod method;
    private final Object requestBody;
    private final Class<?> responseType;
    private final String jsonPath;
    private final Object expectedValue;
    private final Object[] urlVariables;

    private TestArguments(String urlTemplate, HttpMethod method, Object requestBody, Class<?> responseType, String jsonPath, Object expectedValue, Object... urlVariables) {
        this.urlTemplate = urlTemplate;
        this.method = method;
        this.requestBody = requestBody;
        this.responseType = responseType;
        this.jsonPath = jsonPath;
        this.expectedValue = expectedValue;
        this.urlVariables = urlVariables;
    }

    public static TestArguments of(String urlTemplate, HttpMethod method, Object requestBody, Class<?> responseType, String jsonPath, Object expectedValue, Object... urlVariables) {
        return new TestArguments(urlTemplate, method, requestBody, responseType, jsonPath, expectedValue, urlVariables);
    }

    // Getters and a method to execute the request using TestRestTemplate
    public String getUrlTemplate() {
        return urlTemplate;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public Object getRequestBody() {
        return requestBody;
    }

    public Class<?> getResponseType() {
        return responseType;
    }

    public String getJsonPath() {
        return jsonPath;
    }

    public Object getExpectedValue() {
        return expectedValue;
    }

    public Object[] getUrlVariables() {
        return urlVariables;
    }

    public ResponseEntity<?> executeRequest(RestTemplate restTemplate, String baseUrl) {
        HttpEntity<Object> requestEntity = new HttpEntity<>(requestBody);
        return restTemplate.exchange(baseUrl + urlTemplate, method, requestEntity, responseType, urlVariables);
    }
}
