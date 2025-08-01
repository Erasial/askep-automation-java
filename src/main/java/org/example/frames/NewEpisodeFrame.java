package org.example.frames;

import org.example.locators.CommonLocators;
import org.example.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.example.locators.NewEpisodeFrameLocators;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NewEpisodeFrame extends BasePage {


    public NewEpisodeFrame(WebDriver driver) {
        super(driver);
    }

    public void enterEpisodeDiagnose(String diagnose) {
        driver.findElements(NewEpisodeFrameLocators.diagnoseContainerId).get(1).click();
        driver.findElement(NewEpisodeFrameLocators.diagnoseInputClass).sendKeys(diagnose);

        waitTextInElement(CommonLocators.searchbarItemClass, diagnose);
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
        WebElement dateInput = driver.findElements(NewEpisodeFrameLocators.startDateInputClass).get(7);
        dateInput.clear();
        dateInput.sendKeys(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " 09:00");
    }

    public void syncNewEpisode() {
        driver.findElement(NewEpisodeFrameLocators.syncButtonXpath).click();

        waitVisible(NewEpisodeFrameLocators.successMessageXpath);
    }
}
