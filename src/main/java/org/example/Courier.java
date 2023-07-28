package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Courier {
    private String login;
    private String password;
    private String firstName;

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public Courier() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Response sendRequestCreate(Courier newCourier){
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(newCourier)
                .when()
                .post("/api/v1/courier");
        return response;
    }

    public Response sendRequestLogin(Courier newCourier) {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body("{\"login\": \"" + newCourier.login + "\", \"password\": \""  + newCourier.password + "\"}")
                .when()
                .post("/api/v1/courier/login");
        return response;
    }

    public Response sendRequestDelete(String url) {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .when()
                .delete(url);
        return response;
    }
}
