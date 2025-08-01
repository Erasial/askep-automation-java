package org.example.locators;

import org.openqa.selenium.By;

public class EpisodesPageLocators {
    public static final By getNewEpisodeFrameButtonClass = By.xpath("//*[contains(text(), 'Створити новий епізод')]");
    public static final By optionsMenuButtonXpath = By.xpath("//i[@class='v-icon notranslate mdi mdi-dots-vertical theme--light']");
    public static final By getEncountersPageButtonXpath = By.xpath("//a[@class='my-1 justify-start v-btn v-btn--is-elevated v-btn--has-bg theme--light v-size--small primary']");
    public static final By getEndEpisodeFrameButtonXpath = By.xpath("//*[contains(text(), 'Завершити епізод')]");
}
