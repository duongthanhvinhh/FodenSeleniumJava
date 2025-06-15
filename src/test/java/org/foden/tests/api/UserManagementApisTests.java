package org.foden.tests.api;

import io.restassured.response.Response;
import org.foden.api.models.request.LoginRequest;
import org.foden.api.models.request.UserProfileRequest;
import org.foden.api.models.response.LoginResponse;
import org.foden.api.models.response.UserProfileResponse;
import org.foden.api.services.AuthService;
import org.foden.api.services.UserManagementService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserManagementApisTests {
    public String token;
    @BeforeClass
    public void generateToken(){
        LoginRequest loginRequest = new LoginRequest("FodenDuong1999", "Password@01");
        AuthService authService = new AuthService();
        Response response = authService.login(loginRequest); //Serialization occurs here automatically, when we call .body().post()
        LoginResponse loginResponse = response.as(LoginResponse.class); //Deserialization occurs here, convert Json object to Java object
        System.out.println(response.asPrettyString());
        token = loginResponse.getToken();
    }

    @Test(description = "Verify get user info API is working")
    public void getUserInfoTest(){
        UserManagementService userManagementService = new UserManagementService();
        Response response = userManagementService.getProfile(token);
        System.out.println(response.asPrettyString());
        UserProfileResponse userProfileResponse = response.as(UserProfileResponse.class);
        Assert.assertEquals(userProfileResponse.getUsername(), "FodenDuong1999");
    }

    @Test(description = "Verify update profile API is working")
    public void updateProfileTest(){
        UserProfileRequest userProfileRequest = new UserProfileRequest.Builder().email("testemailspam1@gmail.com").firstName("Ngo").lastName("Kinh").mobileNumber("0928178167").build();
        UserManagementService userManagementService = new UserManagementService();
        Response response = userManagementService.updateProfile(token, userProfileRequest);
        System.out.println(response.asPrettyString());
    }
}
