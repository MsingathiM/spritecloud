package com.assessment.PageObjectModels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {
    @FindBy(id = "first-name")
    private WebElement firstNameInput;
    @FindBy(id = "last-name")
    private WebElement lastNameInput;
    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;
    @FindBy(id = "continue")
    private WebElement continueButton;
    @FindBy(id = "finish")
    private WebElement finishButton;
    @FindBy(className = "complete-header")
    private WebElement orderConfirmation;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        postalCodeInput.sendKeys(postalCode);
        continueButton.click();
    }

    public void finishCheckout() {
        finishButton.click();
    }

    public String getOrderConfirmation() {
        return orderConfirmation.getText();
    }
}
