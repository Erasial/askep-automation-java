package org.example.locators;

import org.openqa.selenium.By;

public class EndEpisodeFrameLocators {
    public static final By reasonSelectClass = By.className("w100");
    public static final By dateInputClass = By.className("form-control");  // find 7th element
    public static final By syncButtonXpath = By.xpath("//button[contains(text(), 'Вивантажити')]");
    public static final By syncSuccessAlertXpath = By.xpath("//*[contains(text(), 'успішно завершено')]");
}
