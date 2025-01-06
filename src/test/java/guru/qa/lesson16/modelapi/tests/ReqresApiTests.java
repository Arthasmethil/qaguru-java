package guru.qa.lesson16.modelapi.tests;

import guru.qa.lesson16.modelapi.models.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static guru.qa.lesson16.modelapi.constants.Constants.*;
import static guru.qa.lesson16.modelapi.specs.CreationSpec.creationRequestSpec;
import static guru.qa.lesson16.modelapi.specs.CreationSpec.creationResponseSpec;
import static guru.qa.lesson16.modelapi.specs.DeleteUserSpec.deleteRequestSpec;
import static guru.qa.lesson16.modelapi.specs.DeleteUserSpec.deleteResponseSpec;
import static guru.qa.lesson16.modelapi.specs.LoginSpec.loginRequestSpec;
import static guru.qa.lesson16.modelapi.specs.LoginSpec.loginResponseSpec;
import static guru.qa.lesson16.modelapi.specs.RegistrationSpec.*;
import static guru.qa.lesson16.modelapi.specs.UpdateUserSpec.updateRequestSpec;
import static guru.qa.lesson16.modelapi.specs.UpdateUserSpec.updateResponseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ReqresApiTests extends TestBase {

    @DisplayName("User registration")
    @Tag("API")
    @Test
    void successfulRegistrationTest() {
        RegistrationBodyModel registerData = new RegistrationBodyModel();
        registerData.setEmail(EMAIL);
        registerData.setPassword(REGISTER_PASSWORD);

        RegistrationResponseModel response = step("Make request", () ->
                given()
                        .spec(registrationRequestSpec)
                        .body(registerData)
                        .when()
                        .post()
                        .then()
                        .spec(registrationResponseSpec)
                        .extract().as(RegistrationResponseModel.class));

        step("Check response", () -> Assertions.assertNotNull(response.getToken()));
    }

    @DisplayName("User failed registration")
    @Tag("API")
    @Test
    void unsuccessfulRegistrationTest() {
        RegistrationBodyModel registerData = new RegistrationBodyModel();
        registerData.setEmail(EMAIL);

        RegistrationErrorModel registrationError = step("Make registration request", () ->
                given(registrationRequestSpec)
                        .body(registerData)
                        .when()
                        .post()
                        .then()
                        .spec(unsuccessfulRegistrationResponseSpec)
                        .extract().as(RegistrationErrorModel.class));

        step("Check response", () -> assertThat(registrationError.getError()).isEqualTo("Missing password"));
    }

    @DisplayName("Successful login")
    @Tag("API")
    @Test
    void successfulLoginTest() {
        LoginBodyModel loginBodyModelData = new LoginBodyModel();
        loginBodyModelData.setEmail(EMAIL);
        loginBodyModelData.setPassword(AUTH_PASSWORD);

        LoginResponseModel response = step("Make login request", () ->
                given()
                        .spec(loginRequestSpec)
                        .body(loginBodyModelData)
                        .when()
                        .post()
                        .then()
                        .spec(loginResponseSpec)
                        .extract().as(LoginResponseModel.class));
        step("Check response", () -> assertThat(response.getToken()).isNotNull());
    }

    @DisplayName("Create an user")
    @Tag("API")
    @Test
    void createUserTest() {
        CreationUserBodyModel creationBodyModelData = new CreationUserBodyModel();
        creationBodyModelData.setName(USER_NAME);
        creationBodyModelData.setJob(USER_JOB);

        CreationUserResponseModel response = step("Make create user request", () ->
                given()
                        .spec(creationRequestSpec)
                        .body(creationBodyModelData)
                        .when()
                        .post()
                        .then()
                        .spec(creationResponseSpec)
                        .extract().as(CreationUserResponseModel.class));

        step("Check response", () -> {
            assertThat(response.getName()).isEqualTo(USER_NAME);
            assertThat(response.getJob()).isEqualTo(USER_JOB);
            assertThat(response.getId()).isNotNull();
        });
    }

    @DisplayName("Update an user ")
    @Tag("API")
    @Test
    void updateUserTest() {
        UpdateUserModel updateBodyModelData = new UpdateUserModel();
        updateBodyModelData.setName(USER_NAME);
        updateBodyModelData.setJob(UPDATE_USER_JOB);

        UpdateUserModel response = step("Make create user request", () ->
                given()
                        .spec(updateRequestSpec)
                        .body(updateBodyModelData)
                        .when()
                        .put()
                        .then()
                        .spec(updateResponseSpec)
                        .extract().as(UpdateUserModel.class));

        step("Check response", () -> {
            assertThat(response.getName()).isEqualTo(USER_NAME);
            assertThat(response.getJob()).isEqualTo(UPDATE_USER_JOB);
            assertThat(response.getUpdatedAt()).isNotNull();
        });
    }

    @DisplayName("Delete an user")
    @Tag("API")
    @Test
    void deleteUserTest() {
        int statusCode = step("Make request to delete user", () ->
                given(deleteRequestSpec)
                        .when()
                        .delete()
                        .then()
                        .spec(deleteResponseSpec)
                        .extract().statusCode());

        step("Check response that user was deleted", () ->
                assertThat(statusCode).isEqualTo(204));
    }
}
