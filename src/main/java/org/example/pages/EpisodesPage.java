package org.example.pages;

import org.example.common.Strings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.example.locators.EpisodesPageLocators;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;

public class EpisodesPage extends BasePage{

    public EpisodesPage(WebDriver driver) {
        super(driver);
    }

    public void getNewEpisodeFrame() {
        driver.findElement(EpisodesPageLocators.getNewEpisodeFrameButtonClass).click();
    }

    private void openOptionsMenu(String diagnose, String tooth)
    {
        String episodeName;
        if (diagnose.equals("Z01.2") || diagnose.equals("K03.6"))
            episodeName = Strings.diagnoseFullName.get(diagnose);
        else
            episodeName = " - " + tooth;

        List<WebElement> td = driver.findElements(By.xpath("//tr/td[3]"));

        int i = 0;
        while (!td.get(i).getText().contains(episodeName))
            i++;

        driver.findElements(EpisodesPageLocators.optionsMenuButtonXpath)
                .get(i)
                .click();
    }

    public void searchEpisode2 (String diagnose, String tooth)
    {
        openOptionsMenu(diagnose, tooth);

        driver.get(
                Objects.requireNonNull(driver.findElement(EpisodesPageLocators.getEncountersPageButtonXpath)
                        .getAttribute("href"))
        );

    }

    public void getEndEpisodeFrame2(String diagnose, String tooth)
    {
        openOptionsMenu(diagnose, tooth);

        driver.findElement(EpisodesPageLocators.getEndEpisodeFrameButtonXpath).click();
    }
}
