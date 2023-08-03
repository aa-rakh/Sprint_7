package org.example;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

abstract class BaseHttpClient {

    public ValidatableResponse doGetRequest(String url){
        return given(baseRequest()).get(url).then();

    }
    public ValidatableResponse doPostRequest(String url, Object body){
        RequestSpecification request = given(baseRequest());
        request.body(body);
        return request.post(url).then();
    }
    //    post без body
//    public ValidatableResponse doPostRequest(String url){
//        return given(baseRequest()).post(url).then();
//    }

    public ValidatableResponse doDeleteRequest(String url){
        return given(baseRequest()).delete(url).then();
    }
    private RequestSpecification baseRequest(){
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
               // .addFilter(new RequestLoggingFilter())
              //  .addFilter(new ResponseLoggingFilter())
                .setRelaxedHTTPSValidation()
                .build();
    }
}
