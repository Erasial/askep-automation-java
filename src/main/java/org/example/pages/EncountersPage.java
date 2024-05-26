package org.example.pages;

import org.example.locators.EncountersPageLocators;
import org.openqa.selenium.WebDriver;

public class EncountersPage {
    private final WebDriver driver;

    public EncountersPage(WebDriver driver) {
        this.driver = driver;
    }

    public void getNewEncounterPage() {
        driver.findElement(EncountersPageLocators.getNewEncounterPageButtonLinkText).click();
    }
}

