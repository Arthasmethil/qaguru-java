package guru.qa.lesson9.tests;

import guru.qa.lesson9.data.SauceDemoProduct;
import guru.qa.lesson9.pages.SauceDemoCatalogPage;
import guru.qa.lesson9.pages.SauceDemoItemCardPage;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

public class DataProviderSauceDemoTests extends TestBase {

    SauceDemoCatalogPage sauceDemoCatalogPage = new SauceDemoCatalogPage();
    SauceDemoItemCardPage sauceDemoItemCardPage = new SauceDemoItemCardPage();

    @ValueSource(strings = {
            "Sauce Labs Backpack",
            "Sauce Labs Bike Light",
            "Sauce Labs Bolt T-Shirt"
    })
    @ParameterizedTest(name = "Adding {0} to a basket")
    @Tag("SMOKE")
    @Tag("WEB")
    void addOneItemToBasketViaValueSource(String item) {
        sauceDemoCatalogPage
                .findItemViaDescription(item)
                .addItemViaDescription(item)
                .checkBasketNotEmpty();
    }

    @EnumSource(SauceDemoProduct.class)
    @Tag("WEB")
    @ParameterizedTest(name = "Use enum as a data provider - {0} Card")
    void openItemCardAndCheckName(SauceDemoProduct item) {

        sauceDemoCatalogPage.openItemCard(item.getProductName());

        sauceDemoItemCardPage.checkItemNameIsCorrect(item.getProductName());
    }
}
