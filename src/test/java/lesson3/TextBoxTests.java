package lesson3;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000;
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Arthas");
        $("#lastName").setValue("Menethil");
        $("#userEmail").setValue("lichking@blizzard.com");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("9990009343");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("January");
        $(".react-datepicker__year-select").selectOption("1990");
        $(".react-datepicker__day--001").click();
        $("#subjectsInput").setValue("Maths").pressEnter();
        $("#hobbiesWrapper").$(byText("Music")).click();
        $("#uploadPicture").uploadFromClasspath("guru_png.png");
        $("#currentAddress").setValue("Frozen Throne");
        $("#state").scrollTo().click();
        $("#state").$(byText("Uttar Pradesh")).click();
        $("#city").click();
        $("#city").$(byText("Agra")).click();
        $("#submit").click();

        $$(".table-responsive").filterBy(text("Student Name")).first().shouldHave(text("Arthas Menethil"));
        $$(".table-responsive").filterBy(text("Student Email")).first().shouldHave(text("lichking@blizzard.com"));
        $$(".table-responsive").filterBy(text("Gender")).first().shouldHave(text("Male"));
        $$(".table-responsive").filterBy(text("Mobile")).first().shouldHave(text("9990009343"));
        $$(".table-responsive").filterBy(text("Date of Birth")).first().shouldHave(text("01 January,1990"));
        $$(".table-responsive").filterBy(text("Subjects")).first().shouldHave(text("Maths"));
        $(".table-responsive").$(byText("Hobbies")).sibling(0).shouldHave(text("Music"));
        $(".table-responsive").$(byText("Picture")).sibling(0).shouldHave(text("guru_png.png"));
        $(".table-responsive").$(byText("Address")).sibling(0).shouldHave(text("Frozen Throne"));
        $(".table-responsive").$(byText("State and City")).sibling(0).shouldHave(text("Uttar Pradesh Agra"));
    }
}
