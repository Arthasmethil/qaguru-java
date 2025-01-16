package guru.qa.lesson18.api.auth;

import guru.qa.lesson18.config.AuthConfig;
import guru.qa.lesson18.models.LoginResponseModel;
import guru.qa.lesson18.models.LoginViewModel;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;

import static guru.qa.lesson18.constants.Endpoints.LOGIN_ENDPOINT;
import static guru.qa.lesson18.specs.ApiSpecifications.requestSpec;
import static guru.qa.lesson18.specs.ApiSpecifications.successfulResponseSpec200;
import static io.restassured.RestAssured.given;

public class AuthorizationApi {

    @Step("Add book for an User")
    public static LoginResponseModel authResponse() {
        LoginViewModel authData = new LoginViewModel();
        AuthConfig authConfig = ConfigFactory.create(AuthConfig.class, System.getProperties());
        authData.setUserName(authConfig.username());
        authData.setPassword(authConfig.password());
        return given()
                .spec(requestSpec)
                .body(authData)
                .when()
                .post(LOGIN_ENDPOINT)
                .then()
                .spec(successfulResponseSpec200)
                .extract().as(LoginResponseModel.class);
    }

    public static String authToken = authResponse().getToken();
    public static String authUserId = authResponse().getUserId();
    public static String authExpires = authResponse().getExpires();
}