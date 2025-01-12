package guru.qa.lesson18.tests;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static guru.qa.lesson18.constants.Constants.URI_SERVER;


public class TestBase {

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = URI_SERVER;
        RestAssured.baseURI = URI_SERVER;
    }

    @AfterEach
    void shutDown() {
        closeWebDriver();
    }

}
