package org.example;
import io.restassured.response.ValidatableResponse;

public class OrderHttpClient extends BaseHttpClient {
    private final String url;
    String baseUrl = "https://qa-scooter.praktikum-services.ru";

    public OrderHttpClient() {
        super();
        url = baseUrl + "/api/v1/orders";
    }

    public ValidatableResponse createOrder(OrderData orderData){
        return doPostRequest(url, orderData);
    }
}
