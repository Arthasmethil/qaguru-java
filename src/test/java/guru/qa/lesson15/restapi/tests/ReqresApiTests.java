package guru.qa.lesson15.restapi.tests;

import guru.qa.lesson15.restapi.config.TestConfig;
import guru.qa.lesson15.restapi.constants.Constants;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;

public class ReqresApiTests extends TestConfig {

    @Test
    void successfulRegistrationTest() {
        String registerData = "{\"email\":\"" + Constants.EMAIL + "\", \"password\":\"" + Constants.REGISTER_PASSWORD + "\"}";

        given()
                .body(registerData)
                .contentType(JSON)
                .log().uri()
                .log().body()
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(4))
                .body("token", notNullValue());
    }

    @Test
    void unsuccessfulRegistrationTest() {
        String registerData = "{\"email\":\"" + Constants.EMAIL + "\", \"password\":\"\"}";

        given()
                .body(registerData)
                .contentType(JSON)
                .log().uri()
        .when()
                .post("/register")
        .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void successfulLoginTest() {
        String authData = "{\"email\":\"" + Constants.EMAIL + "\", \"password\":\"" + Constants.AUTH_PASSWORD + "\"}";
        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", notNullValue());

    }

    @Test
    void createUserTest() {
        String createUserData = "{\"name\": \"morpheus\", \"job\": \"leader\"}";
        given()
                .body(createUserData)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"))
                .body("id", notNullValue());
    }

    @Test
    void updateUserTest() {
        String updateUserData = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}";
        given()
                .body(updateUserData)
                .contentType(JSON)
                .log().uri()
                .when()
                .put("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"))
                .body("updatedAt", notNullValue());
    }

    @Test
    void deleteUserTest() {
        given()
                .log().uri()
                .when()
                .delete("/users/2")
                .then()
                .log().status()
                .statusCode(204);
    }
}
