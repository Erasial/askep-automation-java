package org.example.locators;

import org.openqa.selenium.By;

public class NewEpisodeFrameLocators {
    public static By diagnoseContainerId = By.className("select2-selection--single");  // find 3rd element
    public static By diagnoseInputClass = By.className("select2-search__field");
    public static By nameInputId = By.id("episode-name");  // find 1st element
    public static By typeSelectClass = By.className("w100");  // find 2nd element
    public static By startDateInputClass = By.className("form-control");  // find last (8th) element
    public static By syncButtonXpath = By.xpath("//button[contains(text(), 'Створити та вивантажити')]");
}
