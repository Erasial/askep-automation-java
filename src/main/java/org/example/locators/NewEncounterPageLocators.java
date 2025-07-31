package org.example.locators;

import org.openqa.selenium.By;

public class NewEncounterPageLocators {
    public static By dateInputId = By.id("input-87");
    public static By durationInputId = By.id("input-92");
    public static By reasonInputId = By.id("input-114");
    public static By operationInputId = By.id("input-130");
    public static By prioritySelectId = By.id("input-152");
    public static By diagnoseTabLinkXpath = By.xpath("//*[contains(text(), 'Діагнози')]");
    public static By proceduresTabLinkXpath = By.xpath("//*[contains(text(), 'Процедури')]");
    public static By diagnoseTypeSelectId = By.id("input-199");
    public static By newDiagnoseSelectXpath = By.xpath("//*[contains(text(), 'Новий діагноз')]");
    public static By newDiagnoseInputXpath = By.xpath("/html/body/div[2]/main/div[3]/div[1]/div/div[4]/div/div[3]/div/div[2]/div/div/form/div/div[2]/div[1]/div/div/div/div/div/div[2]/div[1]/div/div[2]/div/div[1]/div[1]/input[1]");
    public static By saveButtonXpath = By.xpath("//*[contains(text(), 'Зберегти')]");
    public static By proceedToSyncButtonXpath = By.xpath("//*[contains(text(), 'Вивантажити на eHealth')]");
    public static By addProcedureButtonXpath = By.xpath("//*[contains(text(), 'Додати процедуру')]");
    public static By procedureSuccessButtonXpath = By.xpath("//div[text()='Процедура проведена успішно']");
}
