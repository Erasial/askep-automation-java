package org.example.frames;

import org.example.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.example.locators.EndEpisodeFrameLocators;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EndEpisodeFrame extends BasePage {

    public EndEpisodeFrame(WebDriver driver) {
        super(driver);
    }

    public void selectEndingReason() {
        Select select = new Select(
                driver.findElement(EndEpisodeFrameLocators.reasonSelectClass)
        );

        select.selectByValue("resolved");
    }

    public void enterEndingDate() {
        LocalDateTime dt = LocalDateTime.now();
        dt = dt.minusMinutes(30);

        List<WebElement> list = driver.findElements(EndEpisodeFrameLocators.dateInputClass);
        list.get(list.size() - 2).sendKeys(dt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));
    }

    public void syncEndingEpisode() {
        driver.findElement(EndEpisodeFrameLocators.syncButtonXpath).click();

        waitVisible(EndEpisodeFrameLocators.syncSuccessAlertXpath);
    }
}
