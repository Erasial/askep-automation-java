package org.example.frames;

import org.example.locators.NewEncounterPageLocators;
import org.openqa.selenium.*;
import org.example.locators.SyncFrameLocators;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class SyncFrame {
    private final WebDriver driver;

    public SyncFrame(WebDriver driver) {
        this.driver = driver;
    }

    private void checkForPOOO()
    {
        if (driver.findElement(By.xpath("//li[contains(text(), 'Packets out of order')]")).isDisplayed()) {
            driver.findElement(By.xpath("/html/body/div[5]/div/div[3]/button[1]")).click();
        }

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.elementToBeClickable(driver.findElement(NewEncounterPageLocators.saveButtonXpath))
        );

        driver.findElement(NewEncounterPageLocators.saveButtonXpath).click();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(), 'Взаємодію успішно створено')]")
                )
        );

    }

    public void syncData() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(15)).until(
                    ExpectedConditions.visibilityOfElementLocated(SyncFrameLocators.syncDataButtonXpath)
            );
        } catch (TimeoutException e) {
            checkForPOOO();
        }

        driver.findElement(SyncFrameLocators.syncDataButtonXpath).click();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOfElementLocated(SyncFrameLocators.successAlertClass)
        );
    }
}
