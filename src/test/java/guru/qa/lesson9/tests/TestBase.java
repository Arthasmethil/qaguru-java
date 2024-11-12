package guru.qa.lesson9.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import guru.qa.lesson9.pages.SauceDemoLoginPage;
import guru.qa.lesson9.utils.PropertiesProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {

    private final SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage();
    private final PropertiesProvider propertiesProvider = new PropertiesProvider();

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://www.saucedemo.com/";
        Configuration.pageLoadStrategy = "eager";
        // for debug purpose only
//        Configuration.holdBrowserOpen = true;
    }

    @BeforeEach
    void setup() {
        sauceDemoLoginPage
                .openPage()
                .setUsername(propertiesProvider.getLogin())
                .setPassword(propertiesProvider.getPassword())
                .LogIn();
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }

}
