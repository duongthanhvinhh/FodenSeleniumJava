package org.foden.api.services;

import io.restassured.response.Response;
import org.foden.api.models.request.LoginRequest;
import org.foden.api.models.request.SignUpRequest;

import java.util.HashMap;

public class AuthService extends BaseService{
    private static final String BASE_PATH = "/api/auth";

    public AuthService(){

    }

    public Response login(LoginRequest payload){
        return postRequest(payload, BASE_PATH + "/login");
    }

    public Response signUp(SignUpRequest payload){
        return postRequest(payload, BASE_PATH + "/signup");
    }

    public Response forgotPassword(String emailAddress){
        HashMap<String, String> payload = new HashMap<>();
        payload.put("email", emailAddress);
        return  postRequest(payload, BASE_PATH + "/forgot-password");
    }
}
