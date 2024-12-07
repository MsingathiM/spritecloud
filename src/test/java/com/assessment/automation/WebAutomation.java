package com.assessment.automation;

import com.assessment.PageObjectModels.CartPage;
import com.assessment.PageObjectModels.CheckoutPage;
import com.assessment.PageObjectModels.InventoryPage;
import com.assessment.PageObjectModels.LoginPage;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebAutomation {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/admin/Downloads/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @Test
    public void testLogin() {
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(inventoryPage.getTitle(), "PRODUCTS");
    }

    @Test
    public void testAddToCart() {
        inventoryPage.addItemToCart();
        inventoryPage.goToCart();
        Assert.assertEquals(cartPage.getCartItem(), "Sauce Labs Backpack");
    }

    @Test
    public void testCheckout() {
        cartPage.checkout();
        checkoutPage.fillCheckoutInformation("Msi", "Majola", "98765");
        checkoutPage.finishCheckout();
        Assert.assertEquals(checkoutPage.getOrderConfirmation(), "THANK YOU FOR YOUR ORDER");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}