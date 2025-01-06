package guru.qa.lesson16.modelapi.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static guru.qa.lesson16.modelapi.constants.Constants.REQRES_SERVER;

public class TestBase {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = REQRES_SERVER;
    }

}
