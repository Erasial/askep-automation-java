package org.example.pages;

import org.example.locators.EncountersPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class EncountersPage extends BasePage{


    public EncountersPage(WebDriver driver) {
        super(driver);
    }

    public void getNewEncounterPage() {
        waitClickable(EncountersPageLocators.getNewEncounterPageButtonLinkText).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        if (!driver.findElements(By.xpath("//div[contains(text(), 'Пацієнт перебуває на стаціонарному лікуванні')]")).isEmpty()) {
            driver.findElement(By.xpath("//button[text() = 'Так']")).click();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }
}

