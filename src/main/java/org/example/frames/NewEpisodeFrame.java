package org.example.frames;

import org.example.locators.CommonLocators;
import org.openqa.selenium.WebDriver;
import org.example.locators.NewEpisodeFrameLocators;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NewEpisodeFrame {
    private final WebDriver driver;

    public NewEpisodeFrame(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEpisodeDiagnose(String diagnose) {
        driver.findElements(NewEpisodeFrameLocators.diagnoseContainerId).get(2).click();
        driver.findElement(NewEpisodeFrameLocators.diagnoseInputClass).sendKeys(diagnose);
        driver.findElement(CommonLocators.searchbarItemClass).click();
    }

    public void enterTooth(String tooth) {
        driver.findElement(NewEpisodeFrameLocators.nameInputId).sendKeys(" - " + tooth);
    }

    public void changeEpisodeType() {
        Select select = new Select(
                driver.findElements(NewEpisodeFrameLocators.typeSelectClass).get(1)
        );

        select.selectByValue("DG");
    }

    public void enterEpisodeStartDate(LocalDate date) {
        WebElement dateInput = driver.findElements(NewEpisodeFrameLocators.startDateInputClass).get(8);
        dateInput.clear();
        dateInput.sendKeys(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " 09:00");
    }

    public void syncNewEpisode() {
        driver.findElement(NewEpisodeFrameLocators.syncButtonXpath).click();
    }
}
