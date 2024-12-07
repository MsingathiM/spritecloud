package com.assessment.PageObjectModels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InventoryPage extends BasePage {
    @FindBy(className = "title")
    private WebElement title;
    @FindBy(className = "inventory_item_name")
    private WebElement firstItem;
    @FindBy(className = "btn_primary")
    private WebElement addToCartButton;
    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;


    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return title.getText();
    }

    public void addItemToCart() {
        addToCartButton.click();
    }

    public void goToCart() {
        cartLink.click();
    }
}
