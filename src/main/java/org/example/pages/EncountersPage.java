package org.example.pages;

import org.example.locators.EncountersPageLocators;
import org.example.locators.NewEncounterPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EncountersPage {
    private final WebDriver driver;

    public EncountersPage(WebDriver driver) {
        this.driver = driver;
    }

    public void getNewEncounterPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.elementToBeClickable(
                        driver.findElement(EncountersPageLocators.getNewEncounterPageButtonLinkText))
        );

        driver.findElement(EncountersPageLocators.getNewEncounterPageButtonLinkText).click();

        if (!driver.findElements(By.xpath("//div[contains(text(), 'Пацієнт перебуває на стаціонарному лікуванні')]")).isEmpty()) {
            driver.findElement(By.xpath("//button[text() = 'Так']")).click();
        }
    }
}

