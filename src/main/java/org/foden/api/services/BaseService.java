package org.foden.api.services;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.foden.api.filters.LoggingFilter;

import java.util.Objects;

public class BaseService { //Wrapper for RestAssured

    //Base Uri
    //Creating the request
    //Handling the response

    static {
        RestAssured.filters(new LoggingFilter());
    }

    private RequestSpecification requestSpecification;
    private static final String BASE_URL = "http://64.227.160.186:8080";

    public BaseService(){
        requestSpecification = given().baseUri(BASE_URL);
    }

    protected void setAuthToken(String token){
        requestSpecification.header("Authorization", "Bearer " + token);
    }

    protected Response postRequest(Object payload, String endpoint){
        return requestSpecification.contentType(ContentType.JSON).body(payload).post(endpoint);
    }

    protected Response putRequest(Object payload, String endpoint){
        return requestSpecification.contentType(ContentType.JSON).body(payload).put(endpoint);
    }

    protected Response getRequest(String endpoint){
        return requestSpecification.get(endpoint);
    }
}
