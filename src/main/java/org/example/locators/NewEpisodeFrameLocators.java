package org.example.locators;

import org.openqa.selenium.By;

public class NewEpisodeFrameLocators {
    public static final By diagnoseContainerId = By.className("select2-selection--single");  // find 3rd element
    public static final By diagnoseInputClass = By.className("select2-search__field");
    public static final By nameInputId = By.id("episode-name");  // find 1st element
    public static final By typeSelectClass = By.className("w100");  // find 2nd element
    public static final By startDateInputClass = By.className("form-control");  // find last (8th) element
    public static final By syncButtonXpath = By.xpath("//button[contains(text(), 'Створити та вивантажити')]");
    public static final By successMessageXpath = By.xpath("//*[contains(text(), 'успішно вивантажено')]");
}
