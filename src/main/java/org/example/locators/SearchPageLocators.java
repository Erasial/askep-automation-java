package org.example.locators;

import org.openqa.selenium.By;

public class SearchPageLocators {
    public static final By nameInputId = By.id("search_person_fullname");
    public static final By advancedButtonClass = By.className("advanced-search");
    public static final By birthdateInputId = By.id("search_person_birthday");
    public static final By submitButtonId = By.id("search-patient-submit");  // find 2nd element
    public static final By ehealthIDClass = By.className("ehealth");
}
