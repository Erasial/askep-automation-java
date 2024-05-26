package org.example.locators;

import org.openqa.selenium.By;

public class EncountersPageLocators {
    public static By getNewEncounterPageButtonLinkText = By.xpath("//*[contains(text(), 'Створити взаємодію')]");
    public static By navToEpisodesPageButtonLinkText = By.xpath("//*[contains(text(), 'Перейти до статистики епізодів')]");
    public static By getReferralPageButtonLinkText = By.linkText("Виписати направлення");
}
