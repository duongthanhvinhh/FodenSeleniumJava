package org.foden.tests.api;

import io.restassured.response.Response;
import org.foden.api.models.request.LoginRequest;
import org.foden.api.models.request.SignUpRequest;
import org.foden.api.models.response.LoginResponse;
import org.foden.api.services.AuthService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthApisTests {

    @Test(description = "Verify if Login API is working")
    public void loginTest() {
        LoginRequest loginRequest = new LoginRequest("FodenDuong1999", "Password@01");
        AuthService authService = new AuthService();
        Response response = authService.login(loginRequest); //Serialization occurs here automatically, when we call .body().post()
        LoginResponse loginResponse = response.as(LoginResponse.class); //Deserialization occurs here, convert Json object to Java object
        System.out.println(response.asPrettyString());
        System.out.println(loginResponse.getToken());
        Assert.assertNotEquals(loginResponse.getToken(), null);
    }

    @Test(description = "Verify the SignUp API is working")
    public void signUpTest(){
        SignUpRequest signUpRequest = new SignUpRequest.Builder().username("Foden1112").password("Password@01").email("test1112@gmail.com").firstName("Foden")
                .lastName("Duong").mobileNumber("0917126127"). build();
        AuthService authService = new AuthService();
        Response response = authService.signUp(signUpRequest);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.asPrettyString(), "User registered successfully!");
    }

    @Test(description = "Verify forgot-password API is working")
    public void forgotPasswordTest(){
        AuthService authService = new AuthService();
        Response response = authService.forgotPassword("test20@gmail.com");
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
