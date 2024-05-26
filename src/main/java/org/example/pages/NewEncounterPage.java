package org.example.pages;

import org.example.common.Strings;
import org.example.locators.NewEncounterPageLocators;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.example.common.Common;


public class NewEncounterPage {
    private final WebDriver driver;
    private final Common common;

    public NewEncounterPage(WebDriver driver) {
        this.driver = driver;
        this.common = new Common(driver);
    }

    private int calculateTime(String procedure) {
        if (Arrays.asList(Strings.min5).contains(procedure)) {
            return 5;
        } else if (Arrays.asList(Strings.min10).contains(procedure)) {
            return 10;
        } else if (Arrays.asList(Strings.min20).contains(procedure)) {
            return 20;
        } else {
            return 30;
        }
    }

    private void scrollToNextProcedure(int i, List<String> proceduresList)
    {
        if (i < proceduresList.size() - 1) {
            Actions actions = new Actions(driver);
            actions.scrollByAmount(0, 800).perform();
            driver.findElement(NewEncounterPageLocators.addProcedureButtonXpath).click();

            new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Процедура №" + (i + 2) + "')]"))
            );

            actions.scrollByAmount(0, 100).perform();
        }
    }

    private void enterProcedureDatetime(int procedureTimeID, LocalDate date, LocalTime time)
    {
        WebElement datetimeInput = driver.findElement(By.id("input-" + procedureTimeID));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].removeAttribute('readonly')",
                new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                        ExpectedConditions.elementToBeClickable(datetimeInput)
                ));

        datetimeInput.sendKeys(Keys.CONTROL + "a");
        datetimeInput.sendKeys(Keys.DELETE);
        datetimeInput.sendKeys(date.toString() + " " + time.getHour());
        datetimeInput.sendKeys(":0");



        datetimeInput.click();

        WebElement timeInputButton = common.findLast(NewEncounterPageLocators.timeInputButtonXpath);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.elementToBeClickable(timeInputButton)
        );

        timeInputButton.click();

        WebElement minuteCircleButton = common.findLast(By.xpath("//div[@class='v-picker__title__btn']"));
        minuteCircleButton.click();

        WebElement minuteSelect = common.findLast(By.xpath("//span[text() = " + time.getMinute() + "]")); // TODO prevent converting minutes to int
        minuteSelect.click();

        WebElement timeOKButton = common.findLast(By.xpath("//span[text() = 'Ок']"));
        timeOKButton.click();
    }

    private void enterProcedureCode(int procedureInputID, String procedure)
    {
        driver.findElement(By.id("input-" + procedureInputID)).sendKeys(procedure);
        driver.findElement(By.xpath("//span[contains(text(), '" + procedure + "')]")).click();
    }

    private void enterProcedureResult(int procedureResultID)
    {
        driver.findElement(By.id("input-" + procedureResultID)).click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.elementToBeClickable(common.findLast(NewEncounterPageLocators.procedureSuccessButtonXpath))
        );

        common.findLast(NewEncounterPageLocators.procedureSuccessButtonXpath).click();

    }

    private void checkForPOOO()
    {
        if (driver.findElement(By.xpath("//li[contains(text(), 'Packets out of order')]")).isDisplayed()) {
            driver.findElement(By.xpath("/html/body/div[5]/div/div[3]/button[1]")).click();
        }

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.elementToBeClickable(driver.findElement(NewEncounterPageLocators.saveButtonXpath))
        );

        driver.findElement(NewEncounterPageLocators.saveButtonXpath).click();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(), 'Взаємодію успішно створено')]")
                )
        );

    }

    public void enterEncounterDatetime( List<String> proceduresList, LocalDate date, LocalTime time)
    {
        WebElement datetimeInput = driver.findElement(NewEncounterPageLocators.dateInputId);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].removeAttribute('readonly')",
                new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                        ExpectedConditions.elementToBeClickable(datetimeInput)
                ));

        datetimeInput.sendKeys(Keys.CONTROL + "a");
        datetimeInput.sendKeys(Keys.DELETE);
        datetimeInput.sendKeys(date.toString() + " " + time.getHour());
        datetimeInput.sendKeys(":0");

        datetimeInput.click();

        WebElement dayButton = driver.findElement(NewEncounterPageLocators.dayButtonXpath);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.elementToBeClickable(dayButton)
        );

        dayButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.elementToBeClickable(
                        driver.findElement(By.xpath("//*[@id='app']/div[2]/div/div[1]/" +
                                "div/div[2]/div/div[2]/div/div[1]/div/div/div[2]")))
        );

        Actions actions = new Actions(driver);
        actions.moveToElement(common.findLast(By.xpath("//span[text() = " + time.getHour() + "]")))
                .moveByOffset(3, 3)
                .click()
                .perform();

        common.findLast(By.xpath("//span[text() = " + time.getMinute() + "]")).click(); // TODO prevent converting minutes to int

        driver.findElement(NewEncounterPageLocators.confirmTimeButtonXpath).click();

        int duration = 0;

        for (String procedure : proceduresList) {
            if (Arrays.asList(Strings.min5).contains(procedure)) {
                duration += 5;
            } else if (Arrays.asList(Strings.min10).contains(procedure)) {
                duration += 10;
            } else if (Arrays.asList(Strings.min20).contains(procedure)) {
                duration += 20;
            } else {
                duration += 30;
            }
        }

        driver.findElement(NewEncounterPageLocators.durationInputId).sendKeys(String.valueOf(duration));

    }

    public void selectEncounterReason(String diagnose)
    {
        String reason;
        if (diagnose.equals("Z01.2")) {
            reason = "D31";
        } else {
            reason = "D82";
        }

        driver.findElement(NewEncounterPageLocators.reasonInputId).sendKeys(reason);

        driver.findElement(By.xpath("//span[contains(text(), '" + reason + "')]")).click();
    }

    public void selectOperation()
    {
        String operation = "D67007";

        driver.findElement(NewEncounterPageLocators.operationInputId).sendKeys(operation);

        driver.findElement(By.xpath("//span[contains(text(), '" + operation + "')]")).click();

        Actions actions = new Actions(driver);
        driver.findElement(NewEncounterPageLocators.operationInputId).sendKeys(Keys.ESCAPE);
    }

    public void changePriority()
    {
        Actions actions = new Actions(driver);
        actions.scrollByAmount(0, 200).perform();

        driver.findElement(NewEncounterPageLocators.prioritySelectId).click();
        driver.findElement(By.xpath("//div[contains(text(), 'Плановий')]")).click();
    }

    public void navToDiagnoseTab()
    {
        WebElement diagnoseTab = driver.findElement(NewEncounterPageLocators.diagnoseTabLinkXpath);
        Actions actions = new Actions(driver);
        actions.scrollToElement(diagnoseTab).perform();
        diagnoseTab.click();
    }

    public void selectNewDiagnose(String diagnose)
    {
        driver.findElement(NewEncounterPageLocators.diagnoseTypeSelectId).click();
        driver.findElement(NewEncounterPageLocators.newDiagnoseSelectXpath).click();
        driver.findElement(NewEncounterPageLocators.newDiagnoseInputId).click();
        driver.findElement(By.xpath("//span[contains(text(), '" + diagnose + "')]")).click();
    }

    public void navToProceduresTab()
    {
        WebElement proceduresTab = driver.findElement(NewEncounterPageLocators.proceduresTabLinkXpath);
        Actions actions = new Actions(driver);
        actions.scrollToElement(proceduresTab).perform();
        proceduresTab.click();
    }

    public LocalTime fillInProcedures(List<String> proceduresList, LocalDate date, LocalTime time, boolean isFirstEncounter)
    {
        int procedureInputID, procedureResultID, procedureTimeID, procedureDurationID;

        if (isFirstEncounter) {
            procedureInputID = 499;
            procedureResultID = 523;
            procedureTimeID = 576;
            procedureDurationID = 581;
        } else {
            procedureInputID = 396;
            procedureResultID = 420;
            procedureTimeID = 473;
            procedureDurationID = 478;
        }

        for (int i = 0; i < proceduresList.size(); i++) {
            String procedure = proceduresList.get(i);
            if (procedure.equals("97322")) {
                procedure = "97322-00";
            }

            enterProcedureCode(procedureInputID, procedure);

            Actions actions = new Actions(driver);
            actions.scrollByAmount(0, 400).perform();

            enterProcedureResult(procedureResultID);

            actions.scrollByAmount(0, 400).perform();

            enterProcedureDatetime(procedureTimeID, date, time);

            time = time.plusMinutes(calculateTime(procedure));

            int duration = calculateTime(procedure);
            driver.findElement(By.id("input-" + procedureDurationID)).sendKeys(String.valueOf(duration));

            scrollToNextProcedure(i, proceduresList);

            if (i == 0) {
                procedureInputID += 231;
                procedureResultID += 231;
                procedureTimeID += 231;
                procedureDurationID += 231;
            } else {
                procedureInputID += 229;
                procedureResultID += 229;
                procedureTimeID += 229;
                procedureDurationID += 229;
            }
        }

        time = time.plusMinutes(5);
        return time;
    }

    public void saveEncounter()
    {
        driver.findElement(NewEncounterPageLocators.saveButtonXpath).click();

        try {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(text(), 'Взаємодію успішно створено')]"))
            );
        } catch (TimeoutException e) {
            checkForPOOO();
        }

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(), 'Взаємодію успішно створено')]"))
        );
    }

    public void proceedToSync()
    {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.elementToBeClickable(
                        driver.findElement(NewEncounterPageLocators.proceedToSyncButtonXpath))
        );

        driver.findElement(NewEncounterPageLocators.proceedToSyncButtonXpath).click();
    }
}
