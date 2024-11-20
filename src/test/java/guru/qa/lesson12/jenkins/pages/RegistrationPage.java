package guru.qa.lesson12.jenkins.pages;

import com.codeborne.selenide.SelenideElement;
import guru.qa.lesson12.jenkins.pages.components.CalendarComponent;
import guru.qa.lesson12.jenkins.pages.components.TableFormComponent;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement userEmailInput = $("#userEmail");
    private final SelenideElement genderWrapper = $("#genterWrapper");
    private final SelenideElement userNumberInput = $("#userNumber");
    private final SelenideElement calendarInput = $("#dateOfBirthInput");
    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final SelenideElement hobbiesWrapper = $("#hobbiesWrapper");
    private final SelenideElement uploadPicture = $("#uploadPicture");
    private final SelenideElement currentAddress = $("#currentAddress");
    private final SelenideElement state = $("#state");
    private final SelenideElement city = $("#city");
    private final SelenideElement submit = $("#submit");

    CalendarComponent calendarComponent = new CalendarComponent();
    TableFormComponent tableFormComponent = new TableFormComponent();

    @Step("Open demoqa practice page")
    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        return this;
    }

    @Step("Set Firstname as {0}")
    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    @Step("Set Lastname/Surname as {0}")
    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    @Step("Set Email as {0}")
    public RegistrationPage setEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    @Step("Set gender as {0}")
    public RegistrationPage setGender(String value) {
        genderWrapper.$(byText(value)).click();
        return this;
    }

    @Step("Set user phone number as {0}")
    public RegistrationPage setUserNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }

    @Step("Set date of birth as {0}.{1}.{2}")
    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    @Step("Set subject as {0}")
    public RegistrationPage setSubjectsInput(String value) {
        subjectsInput.setValue(value).pressEnter();
        return this;
    }

    @Step("Set hobbies as {0}")
    public RegistrationPage setHobbiesWrapper(String value) {
        hobbiesWrapper.$(byText(value)).click();
        return this;
    }

    @Step("Upload an image with path {0}")
    public RegistrationPage uploadPicture(String value) {
        uploadPicture.uploadFromClasspath(value);
        return this;
    }

    @Step("Set address as {0}")
    public RegistrationPage setCurrentAddress(String value) {
        currentAddress.setValue(value);
        return this;
    }

    @Step("Set state as {0}")
    public RegistrationPage setState(String value) {
        state.scrollTo().click();
        state.$(byText(value)).click();
        return this;
    }

    @Step("Set city as {0}")
    public RegistrationPage setCity(String value) {
        city.click();
        city.$(byText(value)).click();
        return this;
    }

    @Step("Submit the form")
    public void submit() {
        submit
                .scrollTo()
                .click();
    }

    @Step("Check table has key and value as {0} : {1}")
    public RegistrationPage checkTableResponse(String key, String value) {
        tableFormComponent.checkResult(key, value);
        return this;
    }

    @Step("Check that table doesn't exist")
    public void checkTableNotExist() {
        tableFormComponent.getTableElement().shouldNotBe(visible);
    }


    @Step("Remove banner")
    public RegistrationPage removeBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }
}
