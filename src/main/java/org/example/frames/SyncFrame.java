package org.example.frames;

import org.openqa.selenium.WebDriver;
import org.example.locators.SyncFrameLocators;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class SyncFrame {
    private final WebDriver driver;

    public SyncFrame(WebDriver driver) {
        this.driver = driver;
    }

    public void syncData() {
        driver.findElement(SyncFrameLocators.syncDataButtonClass).click();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOfElementLocated(SyncFrameLocators.successAlertClass)
        );
    }
}
