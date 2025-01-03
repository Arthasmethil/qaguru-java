package guru.qa.lesson15.restapi.tests;

import guru.qa.lesson15.restapi.constants.Constants;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = Constants.REQRES_SERVER;
        RestAssured.basePath = Constants.PATH;
    }

}
