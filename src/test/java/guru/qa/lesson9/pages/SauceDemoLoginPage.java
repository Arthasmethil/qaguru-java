package guru.qa.lesson9.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SauceDemoLoginPage {
    private final SelenideElement usernameInput = $("#user-name");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement loginButton = $("#login-button");
    private final SelenideElement logo = $(".login_logo");

    public SauceDemoLoginPage openPage() {
        open("");
        logo.shouldHave(text("Swag Labs"));
        return this;
    }

    public SauceDemoLoginPage setUsername(String value) {
        usernameInput.setValue(value);
        return this;
    }

    public SauceDemoLoginPage setPassword(String value) {
        passwordInput.setValue(value);
        return this;
    }

    public void LogIn() {
        loginButton.click();
    }
}
