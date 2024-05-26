package org.example.pages;

import org.example.common.Strings;
import org.openqa.selenium.WebDriver;
import org.example.locators.EpisodesPageLocators;
import org.openqa.selenium.WebElement;

public class EpisodesPage {
    private final WebDriver driver;

    public EpisodesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void expandFilter() {
        driver.findElement(EpisodesPageLocators.expandFilterClass).click();
    }

    public void clearEpisodeName() {
        driver.findElements(EpisodesPageLocators.nameInputId).get(12).click();
    }

    public void enterEpisodeName(String diagnose, String tooth) {
        WebElement episodeName = driver.findElements(EpisodesPageLocators.nameInputId).get(12);

        if (diagnose.equals("K03.6") || diagnose.equals("Z01.2")) {
            episodeName.sendKeys(Strings.diagnoseFullName.get(diagnose));
        } else {
            episodeName.sendKeys(Strings.diagnoseFullName.get(diagnose) + " - " + tooth);
        }
    }

    public void submitEpisodeSearch() {
        driver.findElement(EpisodesPageLocators.showButtonId).click();
    }

    public void getEncountersPage() {
        driver.get(
                driver.findElement(EpisodesPageLocators.viewEncountersButtonClass)
                        .getAttribute("href"));
    }

    public void getEndEpisodeFrame() {
        driver.findElement(EpisodesPageLocators.endButtonLinkText).click();
    }

    public void getNewEpisodeFrame() {
        driver.findElement(EpisodesPageLocators.getNewEpisodeFrameButtonClass).click();
    }

    public void openDropdownMenu() {
        driver.findElement(EpisodesPageLocators.dropdownMenuClass).click();
    }
}
