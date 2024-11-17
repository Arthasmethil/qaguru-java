package guru.qa.lesson11.allure;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
        // for debug purpose only
//        Configuration.holdBrowserOpen = true;
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }

}