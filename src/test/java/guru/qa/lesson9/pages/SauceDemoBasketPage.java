package guru.qa.lesson9.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;

public class SauceDemoBasketPage {


    private final ElementsCollection basketItems = $$(".cart_item");

    public void checkBasketNotEmpty() {
        basketItems.shouldBe(CollectionCondition.sizeGreaterThan(0));
    }
}
