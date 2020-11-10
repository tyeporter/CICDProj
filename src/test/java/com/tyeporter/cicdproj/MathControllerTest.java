package com.tyeporter.cicdproj;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = CicdProjApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class MathControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> getEntity;

    // =========================================================
    // Setup
    // =========================================================
    @BeforeEach
    public void setup() {
        restTemplate = new TestRestTemplate();
        headers = new HttpHeaders();
        getEntity = new HttpEntity<>(null, headers);
    }

    @Test
    public void testAddIntegers() {
        ResponseEntity<String> response = restTemplate.exchange(
            createUrlWithPort("/add/3/4"), 
            HttpMethod.GET, 
            getEntity, 
            String.class
        );

        String expected = "7";
        assertEquals(expected, response.getBody());
    }
    
    // =========================================================
    // HELPER METHODS
    // =========================================================
    private String createUrlWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
