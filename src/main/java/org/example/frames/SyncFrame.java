package org.example.frames;

import org.example.locators.NewEncounterPageLocators;
import org.example.pages.BasePage;
import org.openqa.selenium.*;
import org.example.locators.SyncFrameLocators;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SyncFrame extends BasePage {


    public SyncFrame(WebDriver driver) {
        super(driver);
    }

    private void checkForPOOO()
    {
        if (driver.findElement(By.xpath("//li[contains(text(), 'Packets out of order')]")).isDisplayed())
            driver.findElement(By.xpath("/html/body/div[5]/div/div[3]/button[1]")).click();

        waitClickable(NewEncounterPageLocators.saveButtonXpath).click();
        waitVisible(By.xpath("//*[contains(text(), 'Взаємодію успішно створено')]"));

    }

    public void syncData() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // 1. Wait until the button is actually ready to receive a click
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(SyncFrameLocators.syncDataButtonXpath));

            // 2. Click it
            button.click();
        } catch (TimeoutException e) {
            checkForPOOO();
        } catch (ElementClickInterceptedException e) {
            // 3. Fallback: If still intercepted, try a JS Click
            WebElement button = driver.findElement(SyncFrameLocators.syncDataButtonXpath);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }

        waitVisible(SyncFrameLocators.successAlertClass);
    }
}
