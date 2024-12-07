package com.assessment.PageObjectModels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {
    @FindBy(className = "inventory_item_name")
    private WebElement cartItem;
    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getCartItem() {
        return cartItem.getText();
    }

    public void checkout() {
        checkoutButton.click();
    }
}
