package guru.qa.lesson18.specs;

import guru.qa.lesson18.api.auth.AuthorizationApi;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static guru.qa.lesson18.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public class ApiSpecifications {
    public static RequestSpecification requestSpec = with()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .log().all();

    public static RequestSpecification getRequestSpecAuthorizedUser() {
        return with()
                .filter(withCustomTemplates())
                .contentType(JSON)
                .header("Authorization", "Bearer " + AuthorizationApi.getAuthToken())
                .log().all();
    }

    public static RequestSpecification getRequestSpecUserWithTokenAndId() {
        return with()
                .filter(withCustomTemplates())
                .contentType(JSON)
                .header("Authorization", "Bearer " + AuthorizationApi.getAuthToken())
                .queryParams("UserId", AuthorizationApi.getAuthUserId())
                .log().all();
    }

    public static ResponseSpecification creationResponseSpec201 = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(ALL)
            .build();

    public static ResponseSpecification deleteResponseSpec204 = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(ALL)
            .build();

    public static ResponseSpecification successfulResponseSpec200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(ALL)
            .build();

    public static ResponseSpecification responseOnlyWithLogs = new ResponseSpecBuilder()
            .log(ALL)
            .build();
}
