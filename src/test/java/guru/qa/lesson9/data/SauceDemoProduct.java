package guru.qa.lesson9.data;

public enum SauceDemoProduct {
    SAUCE_LABS_BACKPACK("Sauce Labs Backpack"),
    SAUCE_LABS_BIKE_LIGHT("Sauce Labs Bike Light"),
    SAUCE_LABS_BOLT_T_SHIRT("Sauce Labs Bolt T-Shirt"),
    SAUCE_LABS_FLEECE_JACKET("Sauce Labs Fleece Jacket"),
    SAUCE_LABS_ONESIE("Sauce Labs Onesie");

    private final String productName;

    SauceDemoProduct(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }
}
