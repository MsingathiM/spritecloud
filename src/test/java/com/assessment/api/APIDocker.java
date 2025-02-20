package com.assessment.api;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class APIDocker {

        @BeforeClass
        public static void setUp() {
            // Set the base URL for the API
            RestAssured.baseURI = "http://localhost:8080/"; // Adjust as needed
        }

        @Test
        public void testUserAuthentication() {
            // Prepare the login request body
            String requestBody = "{ \"email\": \"testuser@yellowtail.nl\", \"password\": \"Password1!\" }";

            // Send the login request
            Response response = RestAssured.given()
                    .header("Content-Type", "application/json")
                    .body(requestBody)
                    .post("/login");

            // Assert the response status code
            Assert.assertEquals(response.getStatusCode(), 200);

            // Assert the response contains a valid token
            String token = response.jsonPath().getString("token");
            Assert.assertNotNull(token);
        }
    }

