package org.example.locators;

import org.openqa.selenium.By;

public class CommonLocators {
    public static final By searchbarItemClass = By.className("select2-results__option--highlighted");
    public static final By noDataTextXpath = By.xpath("//td[text()='Немає даних для відображення']");
}
