package org.example.locators;

import org.openqa.selenium.By;

public class EndEpisodeFrameLocators {
    public static By reasonSelectClass = By.className("w100");
    public static By dateInputClass = By.className("form-control");  // find 7th element
    public static By syncButtonXpath = By.xpath("//button[contains(text(), 'Вивантажити')]");
    public static By syncSuccessAlertXpath = By.xpath("//*[contains(text(), 'успішно завершено')]");
}
