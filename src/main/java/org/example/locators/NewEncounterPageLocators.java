package org.example.locators;

import org.openqa.selenium.By;

public class NewEncounterPageLocators {
    public static final By dateInputId = By.id("input-87");
    public static final By durationInputId = By.id("input-92");
    public static final By reasonInputId = By.id("input-114");
    public static final By operationInputId = By.id("input-130");
    public static final By prioritySelectId = By.id("input-152");
    public static final By diagnoseTabLinkXpath = By.xpath("//*[contains(text(), 'Діагнози')]");
    public static final By proceduresTabLinkXpath = By.xpath("//*[contains(text(), 'Процедури')]");
    public static final By diagnoseTypeSelectId = By.id("input-199");
    public static final By newDiagnoseSelectXpath = By.xpath("//*[contains(text(), 'Новий діагноз')]");
    public static final By newDiagnoseInputXpath = By.xpath("/html/body/div[2]/main/div[3]/div[1]/div/div[4]/div/div[3]/div/div[2]/div/div/form/div/div[2]/div[1]/div/div/div/div/div/div[2]/div[1]/div/div[2]/div/div[1]/div[1]/input[1]");
    public static final By saveButtonXpath = By.xpath("//*[contains(text(), 'Зберегти')]");
    public static final By proceedToSyncButtonXpath = By.xpath("//*[contains(text(), 'Вивантажити на eHealth')]");
    public static final By addProcedureButtonXpath = By.xpath("//*[contains(text(), 'Додати процедуру')]");
    public static final By procedureSuccessButtonXpath = By.xpath("//div[text()='Процедура проведена успішно']");
}
