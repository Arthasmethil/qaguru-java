package guru.qa.lesson9.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SauceDemoItemCardPage {
    private final SelenideElement basketButton = $(".inventory_details_name");

    public SauceDemoItemCardPage checkItemNameIsCorrect(String name) {
        basketButton.shouldHave(text(name));
        return this;
    }
}
