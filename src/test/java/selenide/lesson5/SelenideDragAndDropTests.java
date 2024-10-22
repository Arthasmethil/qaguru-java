package selenide.lesson5;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.DragAndDropOptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SelenideDragAndDropTests {

    @BeforeAll
    static void setup() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://the-internet.herokuapp.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void selenideActionsDragAndDropTest() {
        open("/drag_and_drop");
        actions()
                .moveToElement($("#column-a"))
                .clickAndHold()
                .moveToElement($("#column-b"))
                .release()
                .perform();
        $("#column-b").shouldHave(text("A"));
    }

    @Test
    void selenideDragAndDropOptionsTest() {
        open("/drag_and_drop");
        $("#column-a").dragAndDrop(DragAndDropOptions.to($("#column-b")));
        $("#column-a").shouldHave(text("B"));
    }
}
