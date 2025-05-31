package org.example.locators;

import org.openqa.selenium.By;

public class SearchPageLocators {
    public static By nameInputId = By.id("search_person_fullname");
    public static By advancedButtonClass = By.className("advanced-search");
    public static By birthdateInputId = By.id("search_person_birthday");
    public static By submitButtonId = By.id("search-patient-submit");  // find 2nd element
    public static By ehealthIDClass = By.className("ehealth");
}
