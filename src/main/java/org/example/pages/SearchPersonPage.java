package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.example.locators.SearchPageLocators;

public class SearchPersonPage extends BasePage{

    public SearchPersonPage(WebDriver driver) {
        super(driver);
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
        waitVisible(SearchPageLocators.ehealthIDClass);
        String ehealthId = driver.findElement(SearchPageLocators.ehealthIDClass).getText();
        driver.get("https://askep.net/doctor/medical-events/episodes?person_id=" + ehealthId.substring(10));
    }
}
