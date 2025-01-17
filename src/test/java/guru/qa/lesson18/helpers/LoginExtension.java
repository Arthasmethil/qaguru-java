package guru.qa.lesson18.helpers;

import guru.qa.lesson18.api.auth.AuthorizationApi;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static guru.qa.lesson18.constants.Constants.ICON_URL;

public class LoginExtension implements BeforeEachCallback {

    private static final String USER_ID_SESSION = "userID";
    private static final String EXPIRES_TOKEN_SESSION = "expires";
    private static final String TOKEN_SESSION = "token";

    @Override
    @Step("Add Cookie to browser for Authorization")
    public void beforeEach(ExtensionContext context) {
        open(ICON_URL);
        getWebDriver().manage().addCookie(new Cookie(USER_ID_SESSION, AuthorizationApi.getAuthUserId()));
        getWebDriver().manage().addCookie(new Cookie(EXPIRES_TOKEN_SESSION, AuthorizationApi.getAuthExpires()));
        getWebDriver().manage().addCookie(new Cookie(TOKEN_SESSION, AuthorizationApi.getAuthToken()));
    }

}
