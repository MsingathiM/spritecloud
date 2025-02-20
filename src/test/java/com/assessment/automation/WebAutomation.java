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
import org.openqa.selenium.chrome.ChromeOptions;

public class WebAutomation {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static InventoryPage inventoryPage;
    private static CartPage cartPage;
    private static CheckoutPage checkoutPage;

    @BeforeClass
    public static void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");                      // Required for Linux
        options.addArguments("--headless");                        // Run in headless mode
        options.addArguments("--disable-dev-shm-usage");          // Overcome limited resource problems
        options.addArguments("--remote-debugging-port=9222");     // Enable remote debugging
        options.addArguments("--disable-gpu");                    // Disable GPU hardware acceleration
        options.addArguments("--window-size=1920,1080");         // Set window size
        
        driver = new ChromeDriver(options);
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
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addItemToCart();
        inventoryPage.goToCart();
        Assert.assertEquals(cartPage.getCartItem(), "Sauce Labs Backpack");
    }

    @Test
    public void testCheckout() {
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addItemToCart();
        inventoryPage.goToCart();
        cartPage.checkout();
        checkoutPage.fillCheckoutInformation("Msi", "Majola", "98765");
        checkoutPage.finishCheckout();
        Assert.assertEquals(checkoutPage.getOrderConfirmation(), "THANK YOU FOR YOUR ORDER");
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
