package guru.qa.lesson15.restapi.config;

import guru.qa.lesson15.restapi.constants.Constants;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestConfig {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = Constants.REQRES_SERVER;
        RestAssured.basePath = Constants.PATH;
    }

}
