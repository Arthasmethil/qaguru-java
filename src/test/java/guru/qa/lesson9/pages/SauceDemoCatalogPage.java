package guru.qa.lesson9.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import guru.qa.lesson9.data.SauceDemoProduct;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SauceDemoCatalogPage {
    private final ElementsCollection catalogItems = $$(".inventory_item");
    private final SelenideElement basketButton = $(".shopping_cart_link");
    private final SelenideElement basketBadge = $(".shopping_cart_badge");

    public SauceDemoCatalogPage findItemViaDescription(String description) {
        catalogItems.findBy(text(description)).$(".inventory_item_description")
                .shouldHave(text(description));
        return this;
    }

    public SauceDemoCatalogPage addItemViaDescription(String description) {
        catalogItems.findBy(text(description)).$(".btn").click();
        return this;
    }

    public void openItemCard(String description) {
        catalogItems
                .findBy(text(description))
                .find(".inventory_item_name ")
                .click();
    }


    public void checkBasketNotEmpty() {
        basketBadge.shouldBe(visible);
    }

    public SauceDemoCatalogPage addItemsToBasket(SauceDemoProduct[] products) {
        for (SauceDemoProduct product : products) {
            catalogItems.findBy(text(product.getProductName())).$(".btn").click();
        }
        return this;
    }
    public void goToBasket() {
        basketButton.click();
    }
}
