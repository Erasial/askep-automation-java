package org.example.frames;

import org.example.locators.NewEncounterPageLocators;
import org.example.pages.BasePage;
import org.openqa.selenium.*;
import org.example.locators.SyncFrameLocators;

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
        try {
            waitVisible(SyncFrameLocators.syncDataButtonXpath);
        } catch (TimeoutException e) {
            checkForPOOO();
        }

        driver.findElement(SyncFrameLocators.syncDataButtonXpath).click();

        waitVisible(SyncFrameLocators.successAlertClass);
    }
}
