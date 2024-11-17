package guru.qa.lesson11.allure;

import com.codeborne.selenide.Condition;
import guru.qa.lesson11.allure.locators.Locators;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {
    @Step("Open Github main page")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Looking for repository {repo}")
    public void searchForRepository(String repo) {
        $(Locators.SEARCH_BUTTON).click();
        $(Locators.SEARCH_FIELD).sendKeys(repo);
        $(Locators.SEARCH_FIELD).submit();
    }

    @Step("Click on repository's link {repo}")
    public void clickOnRepositoryLink(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Open tab Issues")
    public void openIssuesTab() {
        $(Locators.ISSUES_TAB).click();
    }

    @Step("Check Issue with name exists {issue}")
    public void shouldSeeIssueWithName(String issue) {
        $(withText(issue)).should(Condition.exist);
    }
}