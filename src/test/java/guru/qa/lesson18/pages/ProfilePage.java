package guru.qa.lesson18.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.SneakyThrows;

import static com.codeborne.selenide.Selenide.*;
import static guru.qa.lesson18.constants.Constants.PROFILE_URL;
import static org.assertj.core.api.Assertions.assertThat;

public class ProfilePage {
    private final SelenideElement reactTable = $(".ReactTable");
    private final SelenideElement deleteOkButton = $("#closeSmallModal-ok");
    private final ElementsCollection bookRows = $$("div.rt-tr-group");

    @Step("Open demoqa profile page")
    public ProfilePage openProfilePage() {
        open(PROFILE_URL);
        return this;
    }


    @Step("Check added book")
    public void checkAddedBook (String titleBook) {
        assertThat(reactTable.getText()).contains(titleBook);
    }

    @Step("Check added book")
    public void checkBookWasDeletedBook (String deletedBookTitle) {
        assertThat(reactTable.getText()).doesNotContain(deletedBookTitle);
    }

    @SneakyThrows
    @Step("Delete book by title")
    public void deleteByTitle (String title) {
        SelenideElement bookRow = bookRows.findBy(Condition.text(title));
        bookRow.$(".action-buttons #delete-record-undefined").click();
        deleteOkButton
                .shouldBe(Condition.interactable)
                .click();
        getFocusedElement()
                .pressEnter();
    }

    @Step("Remove banner")
    public ProfilePage removeBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }
}
