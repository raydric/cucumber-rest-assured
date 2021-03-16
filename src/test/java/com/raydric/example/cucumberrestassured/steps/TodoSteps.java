package com.raydric.example.cucumberrestassured.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TodoSteps {

    private String path;
    private Response response;

    @Given("Todo rest endpoint exists")
    public void restEndpointExists() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        path = "/todos";
    }

    @When("I get list of todos")
    public void getListOfTodos() {
        response = given()
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .get(path)
                .then().extract().response();
    }

    @When("I get todo by id {int}")
    public void getTodoById(int id) {
        response = given()
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .get(path + "/" + id)
                .then().extract().response();
    }

    @Then("Response status code should be {int}")
    public void expectedStatusCode(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @And("Response contains list of todos")
    public void containsListOfTodos() {
        assertEquals(200, response.jsonPath().getList("$").size());
    }

    @And("Response contains todo with id {int}")
    public void containsSingleTodo(int id) {
        int actualId = response.jsonPath().get("id");
        assertEquals(id, actualId);
    }

    @When("I create todo")
    public void createTodo() {
        JSONObject body = new JSONObject();
        body.put("userId", 1);
        body.put("title", "Test");
        body.put("completed", true);

        response = given()
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(body.toString())
                .post(path)
                .then().extract().response();
    }

    @And("Response contains created todo")
    public void containsCreatedTodo() {
        Integer actualId = response.jsonPath().get("id");
        assertNotNull(actualId);
    }

    @When("I delete todo with id {int}")
    public void deleteTodo(int id) {
        response = given()
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .delete(path + "/" + id)
                .then().extract().response();
    }

    @When("I change todo title with id {int} to {word}")
    public void changeTitle(int id, String title) {
        JSONObject body = new JSONObject();
        body.put("title", title);
        body.put("id", id);

        response = given()
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(body.toString())
                .patch(path + "/" + id)
                .then().extract().response();
    }

    @When("I replace todo with id {int} with another")
    public void replaceTodo(int id) {
        JSONObject body = new JSONObject();
        body.put("userId", 1);
        body.put("title", "Test");
        body.put("completed", true);
        body.put("id", id);

        response = given()
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(body.toString())
                .put(path + "/" + id)
                .then().extract().response();
    }
}
