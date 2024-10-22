package selenide.lesson5;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SelenideHoverTests {

    @BeforeAll
    static void setup() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void solutionsHoverTest() {
        open("/");
        $(".HeaderMenu-nav").$(byText("Solutions")).hover();
        $(".HeaderMenu-nav").$(byTagAndText("a", "Enterprises")).click();
        $("#hero-section-brand-heading").shouldHave(text("The AI-powered developer platform."));
    }
}
