package org.example.locators;

import org.openqa.selenium.By;

public class EpisodesPageLocators {
    public static By getNewEpisodeFrameButtonClass = By.xpath("//*[contains(text(), 'Створити новий епізод')]");
    public static By nameInputId = By.tagName("input");  // 13th elem
    public static By showButtonId = By.xpath("//*[contains(text(), 'Застосувати')]");
    public static By viewEncountersButtonClass = By.xpath("/html/body/div[2]/main/div[4]/div[2]/div/a[1]");
    public static By endButtonLinkText = By.xpath("//*[contains(text(), 'Завершити епізод')]");
    public static By expandFilterClass = By.className("v-expansion-panel-header__icon");
    public static By dropdownMenuClass = By.className("mdi-dots-vertical");
}
