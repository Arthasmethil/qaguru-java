package guru.qa.lesson4;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelectorSelenideTheoryTests {

    @BeforeAll
    static void setupConfig() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void chainedElementTest() {
        open("/guru/qa/lesson9/selenide");
        $("a#wiki-tab").click();
        $("#wiki-pages-filter").setValue("SoftAssertions");
        $("#wiki-pages-box").$(byText("SoftAssertions")).shouldBe(visible).click();

        // There is a chained selector that looks for the first match of div.markdown-heading
        // and h4 in the DOM (the element exists).
        $("div.markdown-heading h4").shouldBe(visible);
    }

    @Test
    void unchainedElementTest() {
        open("/guru/qa/lesson9/selenide");
        $("a#wiki-tab").click();
        $("#wiki-pages-filter").setValue("SoftAssertions");
        $("#wiki-pages-box").$(byText("SoftAssertions")).shouldBe(visible).click();

        // There is an unchained selector that looks for the first match of div.markdown-heading,
        // and within this element, it searches for h4 step by step (it might not exist).
        $("div.markdown-heading h6").shouldNotBe(visible);
    }

    @Test
    void thereIsNoChainedElementH6Test() {
        open("/guru/qa/lesson9/selenide");
        $("a#wiki-tab").click();
        $("#wiki-pages-filter").setValue("SoftAssertions");
        $("#wiki-pages-box").$(byText("SoftAssertions")).shouldBe(visible).click();

        // There is a chained selector, but the element does not exist.
        $("div.markdown-heading h6").shouldNotBe(visible);
    }

}
