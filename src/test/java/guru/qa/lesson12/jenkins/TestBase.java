package guru.qa.lesson12.jenkins;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000;

        // for debug purpose only
//        Configuration.holdBrowserOpen = true;
    }

    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }

}
