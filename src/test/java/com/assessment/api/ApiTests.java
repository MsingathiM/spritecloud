package com.assessment.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class ApiTests {

    @Test
    public void testGetUser() {
        Response response = RestAssured.get("https://reqres.in/api/users/2");
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("Janet", response.jsonPath().getString("data.first_name"));
        Assert.assertEquals("Weaver", response.jsonPath().getString("data.last_name"));
    }

    @Test
    public void testCreateUser() {
        String requestBody = "{ \"name\": \"Msingathi\", \"job\": \"leader\" }";
        Response response = RestAssured.given().body(requestBody).post("https://reqres.in/api/users");
        Assert.assertEquals(201, response.getStatusCode());
        Assert.assertNotNull(response.jsonPath().getString("id"));
    }

    @Test
    public void testDeleteUser() {
        Response response = RestAssured.delete("https://reqres.in/api/users/2");
        Assert.assertEquals(204, response.getStatusCode());
    }
}
