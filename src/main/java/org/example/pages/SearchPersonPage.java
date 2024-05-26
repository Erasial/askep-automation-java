package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.example.locators.SearchPageLocators;
import org.openqa.selenium.WebElement;

public class SearchPersonPage {
    private final WebDriver driver;

    public SearchPersonPage(WebDriver driver) {
        this.driver = driver;
    }

    public void getSearchPage() {
        driver.get("https://askep.net/doctor/search-patient");
    }

    public void enterName(String name) {
        driver.findElement(SearchPageLocators.nameInputId).sendKeys(name);
    }

    public void enterBirthdate(String birthdate) {
        driver.findElement(SearchPageLocators.advancedButtonClass).click();
        driver.findElement(SearchPageLocators.birthdateInputId).sendKeys(birthdate);
    }

    public void submitPersonSearch() {
        driver.findElements(SearchPageLocators.submitButtonId).get(1).click();
    }

    public void getEpisodesPage() {
        String ehealthId = driver.findElement(SearchPageLocators.ehealthIDClass).getText();
        driver.get("https://askep.net/doctor/medical-events/episodes?person_id=" + ehealthId.substring(10));
    }
}
