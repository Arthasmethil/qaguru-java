package guru.qa.lesson11.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.lesson11.allure.locators.Locators;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class AllureTests extends TestBase {

    private final String URL = "https://github.com";
    private final String REPOSITORY = "eroshenkoam/allure-example";
    private final String ISSUE_NAME = "One piece";

    @DisplayName("Check issue's name via pure Selenide")
    @Test
    public void testIssueSearchTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open(URL);

        $(Locators.SEARCH_BUTTON).click();
        $(Locators.SEARCH_FIELD).sendKeys(REPOSITORY);
        $(Locators.SEARCH_FIELD).submit();

        $(linkText(REPOSITORY)).click();
        $(Locators.ISSUES_TAB).click();
        $(withText(ISSUE_NAME)).should(Condition.exist);
    }

    @DisplayName("Check issue's name via lambda steps")
    @Test
    public void testLambdaStepTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open Github main page", () -> open(URL));
        step("Looking for repository " + REPOSITORY, () -> {
            $(Locators.SEARCH_BUTTON).click();
            $(Locators.SEARCH_FIELD).sendKeys(REPOSITORY);
            $(Locators.SEARCH_FIELD).submit();
        });
        step("Click on repository's link " + REPOSITORY, () -> $(linkText(REPOSITORY)).click());
        step("Open tab Issues", () -> $(Locators.ISSUES_TAB).click());
        step("Check Issue with name exists " + ISSUE_NAME, () -> {
            $(withText(ISSUE_NAME)).should(Condition.exist);
        });
    }

    @DisplayName("Check issue's name via annotated step")
    @Test
    public void testAnnotatedStepTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithName(ISSUE_NAME);
    }
}
