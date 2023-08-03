package org.example;
import io.restassured.response.ValidatableResponse;

public class CourierHttpClient extends BaseHttpClient{
    //    ручка
    private final String url;
    String baseUrl = "https://qa-scooter.praktikum-services.ru";

    public CourierHttpClient() {
        super();
        url = baseUrl + "/api/v1/courier/";
    }

    public ValidatableResponse createCourier(CourierDTO courierDTO){
        return doPostRequest(url, courierDTO);
    }

    public ValidatableResponse loginCourier(CourierDTO courierDTO){
        return doPostRequest(url + "login", courierDTO);
    }

    public ValidatableResponse deleteCourier(String id) {
        return doDeleteRequest(url + id);
    }
}





