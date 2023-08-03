package org.example;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrdersListHttpClient extends BaseHttpClient {

    private final String url;
    String baseUrl = "https://qa-scooter.praktikum-services.ru";

    public OrdersListHttpClient() {
        super();
        url = baseUrl + "/api/v1/orders";
    }
    public ValidatableResponse getOrdersList(){
        return doGetRequest(url);
    }
}
