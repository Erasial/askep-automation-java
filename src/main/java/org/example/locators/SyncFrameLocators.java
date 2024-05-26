package org.example.locators;

import org.openqa.selenium.By;

public class SyncFrameLocators {
    public static By successAlertClass = By.className("success--text");
    public static By syncDataButtonClass = By.className("mb-3");
    public static By goToEpisodes = By.xpath("//span[contains(text(), 'Перейти до амбулаторних епізодів пацієнта')]");
}
