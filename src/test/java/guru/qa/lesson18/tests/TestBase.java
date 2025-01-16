package guru.qa.lesson18.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.lesson18.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static guru.qa.lesson18.constants.Constants.URI_SERVER;


public class TestBase {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = URI_SERVER;
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.baseUrl = URI_SERVER;
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion");
        Configuration.remote = System.getProperty("remoteHost");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void shutDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.addVideo();
        Attach.pageSource();
        Attach.browserConsoleLogs();
        closeWebDriver();
    }
}
