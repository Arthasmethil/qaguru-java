package guru.qa.lesson16.modelapi.tests;

import guru.qa.lesson16.modelapi.models.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static guru.qa.lesson16.modelapi.constants.Constants.*;
import static guru.qa.lesson16.modelapi.constants.Endpoints.*;
import static guru.qa.lesson16.modelapi.specs.ApiSpecifications.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ReqresApiTests extends TestBase {

    @DisplayName("User registration")
    @Tag("API")
    @Test
    void successfulRegistrationTest() {
        UserBodyModel registerData = new UserBodyModel();
        registerData.setEmail(EMAIL);
        registerData.setPassword(REGISTER_PASSWORD);

        RegistrationResponseModel response = step("Make request", () ->
                given()
                        .spec(requestSpec)
                        .body(registerData)
                        .when()
                        .post(REGISTER_ENDPOINT)
                        .then()
                        .spec(successfulResponseSpec200)
                        .extract().as(RegistrationResponseModel.class));

        step("Check response", () -> Assertions.assertNotNull(response.getToken()));
    }

    @DisplayName("User failed registration")
    @Tag("API")
    @Test
    void unsuccessfulRegistrationTest() {
        UserBodyModel registerData = new UserBodyModel();
        registerData.setEmail(EMAIL);

        RegistrationErrorModel registrationError = step("Make registration request", () ->
                given(requestSpec)
                        .body(registerData)
                        .when()
                        .post(REGISTER_ENDPOINT)
                        .then()
                        .spec(errorResponseSpec400)
                        .extract().as(RegistrationErrorModel.class));

        step("Check response", () -> assertThat(registrationError.getError()).isEqualTo("Missing password"));
    }

    @DisplayName("Successful login")
    @Tag("API")
    @Test
    void successfulLoginTest() {
        UserBodyModel userBodyModelData = new UserBodyModel();
        userBodyModelData.setEmail(EMAIL);
        userBodyModelData.setPassword(AUTH_PASSWORD);

        LoginResponseModel response = step("Make login request", () ->
                given()
                        .spec(requestSpec)
                        .body(userBodyModelData)
                        .when()
                        .post(LOGIN_ENDPOINT)
                        .then()
                        .spec(successfulResponseSpec200)
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
                        .spec(requestSpec)
                        .body(creationBodyModelData)
                        .when()
                        .post(GENERAL_USERS_ENDPOINT)
                        .then()
                        .spec(creationResponseSpec201)
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
                        .spec(requestSpec)
                        .body(updateBodyModelData)
                        .when()
                        .put(USERS_2_ENDPOINT)
                        .then()
                        .spec(successfulResponseSpec200)
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
                given(requestSpec)
                        .when()
                        .delete(USERS_2_ENDPOINT)
                        .then()
                        .spec(deleteResponseSpec204)
                        .extract().statusCode());

        step("Check response that user was deleted", () ->
                assertThat(statusCode).isEqualTo(204));
    }
}
