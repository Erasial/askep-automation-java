package org.example.locators;

import org.openqa.selenium.By;

public class NewEncounterPageLocators {
    public static By dateInputId = By.id("input-77");
    public static By timeButtonXpath = By.xpath("//*[@id='app']/div[2]/div/div[1]/div/div[1]/div[2]/div/div[3]/i");
    public static By minuteDivXpath = By.xpath("//*[@id='app']/div[2]/div/div[1]/div/div[2]/div/div[2]/div/div[1]/div/div/div[2]");
    public static By confirmTimeButtonXpath = By.xpath("//*[@id='app']/div[2]/div/div[2]/button[2]/span");
    public static By durationInputId = By.id("input-82");
    public static By reasonInputId = By.id("input-98");
    public static By operationInputId = By.id("input-109");
    public static By prioritySelectId = By.id("input-131");
    public static By diagnoseTabLinkXpath = By.xpath("//*[contains(text(), 'Діагнози')]");
    public static By proceduresTabLinkXpath = By.xpath("//*[contains(text(), 'Процедури')]");
    public static By diagnoseTypeSelectId = By.id("input-175");
    public static By newDiagnoseSelectXpath = By.xpath("//*[contains(text(), 'Новий діагноз')]");
    public static By existingDiagnoseSelectXpath = By.xpath("//*[contains(text(), 'Діагноз, встановлений під час попередніх взаємодій')]");
    public static By newDiagnoseInputId = By.id("input-369");
    public static By saveButtonXpath = By.xpath("//*[contains(text(), 'Зберегти')]");
    public static By proceedToSyncButtonXpath = By.xpath("//*[contains(text(), 'Вивантажити на eHealth')]");
    public static By procedureInputId = By.id("input-446");
    public static By procedureResultId = By.id("input-464");
    public static By procedureTimeId = By.id("input-1030");
    public static By addProcedureButtonXpath = By.xpath("//*[contains(text(), 'Додати процедуру')]");
    public static By dayButtonXpath = By.xpath("//button[@class = 'v-btn v-btn--active v-btn--rounded theme--light accent']");
    public static By timeInputButtonXpath = By.xpath("//i[@class='v-icon notranslate mdi mdi-clock-outline theme--light']");
    public static By procedureSuccessButtonXpath = By.xpath("//div[contains(text(), 'Процедура проведена успішно')]");
}
