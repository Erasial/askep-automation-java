package org.example.locators;

import org.openqa.selenium.By;

public class CommonLocators {
    public static By searchbarItemClass = By.className("select2-results__option--highlighted");
    public static By noDataTextXpath = By.xpath("//td[text()='Немає даних для відображення']");
}
