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
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.interactions.Actions;

import org.example.common.Common;


public class NewEncounterPage {
    private final WebDriver driver;
    private final Common common;

    public NewEncounterPage(WebDriver driver) {
        this.driver = driver;
        this.common = new Common(driver);
    }

    public void clickWithDot(WebElement el, int xOff, int yOff) {
        new Actions(driver)
                .moveToElement(el, xOff, yOff)
                .click()
                .perform();
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

    private void enterProcedureDatetime(WebElement procedureTime, LocalDate date, LocalTime time)
    {
        String rightDate = common.convertDate(date.toString()).replace(".", "") + time.toString().replace(":", "");
        procedureTime.sendKeys(rightDate);
    }

    private void enterProcedureCode(WebElement procedureInput, String procedure)
    {
        procedureInput.sendKeys(procedure);
        driver.findElement(By.xpath("//span[contains(text(), '" + procedure + "')]")).click();
    }

    private void enterProcedureResult(WebElement procedureResult)
    {
        procedureResult.click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(
                ExpectedConditions.visibilityOfElementLocated(NewEncounterPageLocators.procedureSuccessButtonXpath)
        );

        common.findLast(NewEncounterPageLocators.procedureSuccessButtonXpath).click();

    }

    public void enterEncounterDatetime( List<String> proceduresList, LocalDate date, LocalTime time)
    {
        WebElement datetimeInput = driver.findElement(NewEncounterPageLocators.dateInputId);
        String rightDate = common.convertDate(date.toString()).replace(".", "") + time.toString().replace(":", "");
        datetimeInput.sendKeys(rightDate);

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

        driver.findElement(NewEncounterPageLocators.durationInputId).click();
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
        driver.findElement(NewEncounterPageLocators.reasonInputId).sendKeys(Keys.ESCAPE);
    }

    public void selectOperation()
    {
        driver.findElement(NewEncounterPageLocators.operationInputId).sendKeys("D67007");
        driver.findElement(By.xpath("//span[contains(text(), '" + "D67007" + "')]")).click();

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
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.elementToBeClickable(
                        NewEncounterPageLocators.diagnoseTypeSelectId)
        );

        driver.findElement(NewEncounterPageLocators.diagnoseTypeSelectId).click();
        driver.findElement(NewEncounterPageLocators.newDiagnoseSelectXpath).click();

        driver.findElement(By.xpath("/html/body/div[2]/main/div[3]/div/div/div[4]/div/div[3]/div/div[2]/div/div/form/div/div[2]/div[1]/div/div/button/div[1]")).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("/html/body/div[2]/main/div[3]/div[1]/div/div[4]/div/div[3]/div/div[2]/div/div/form/div/div[2]/div[1]/div/div/div/div/div/div/div[2]/div/div[1]/div[1]/div[1]/input"))
        );
        driver.findElement(By.xpath("/html/body/div[2]/main/div[3]/div[1]/div/div[4]/div/div[3]/div/div[2]/div/div/form/div/div[2]/div[1]/div/div/div/div/div/div/div[2]/div/div[1]/div[1]/div[1]/input")).click();
        driver.findElement(NewEncounterPageLocators.newDiagnoseSelectXpath).click();

        driver.findElement(NewEncounterPageLocators.newDiagnoseInputXpath).sendKeys(diagnose);
//        try {
//            driver.findElement(NewEncounterPageLocators.newDiagnoseSelectXpath).click();
//        } catch (NoSuchElementException e) {
//            System.out.println("cathed no new diagnose button");
//            driver.findElement(NewEncounterPageLocators.diagnoseTypeSelectId).click();
//        }
//
//        try {
//            driver.findElement(NewEncounterPageLocators.newDiagnoseInputXpath).sendKeys(diagnose);
//        } catch (NoSuchElementException e) {
//            driver.findElement(NewEncounterPageLocators.newDiagnoseSelectXpath).click();
//            driver.findElement(NewEncounterPageLocators.newDiagnoseInputXpath).sendKeys(diagnose);
//        }

        driver.findElement(By.xpath("//span[contains(text(), '" + diagnose + "')]")).click();

        Actions actions = new Actions(driver);
        actions.scrollByAmount(0, 100).perform();
    }

    public void navToProceduresTab()
    {
        WebElement proceduresTab = driver.findElement(NewEncounterPageLocators.proceduresTabLinkXpath);
        Actions actions = new Actions(driver);
        actions.scrollToElement(proceduresTab).perform();
        driver.findElements(By.xpath("//i[@class='v-icon notranslate mdi mdi-chevron-right theme--light']")).get(2).click();
        proceduresTab.click();
    }

    public LocalTime fillInProcedures(List<String> proceduresList, LocalDate date, LocalTime time, boolean isFirstEncounter, boolean isAdult)
    {
        for (int i = 0; i < proceduresList.size(); i++) {
            WebElement procedureInput, procedureResult, procedureTime, procedureDuration;

            procedureInput = driver.findElements(By.xpath("//div[text()='Медична послуга']/../..//input[@placeholder='Оберіть']")).get(i);
            procedureResult = driver.findElement(By.xpath("//div[text()='Результат проведення процедури']/..//input[@placeholder='Оберіть']"));
            procedureTime = driver.findElements(By.xpath("//div[contains(text(), 'Період проведення (від)')]/..//input")).get(i);
            procedureDuration = driver.findElements(By.xpath("//div[text()='Тривалість']/..//input")).get(i + 1);

            String procedure = proceduresList.get(i);
            if (procedure.equals("97322")) {
                procedure = "97322-00";
            }

            enterProcedureCode(procedureInput, procedure);

            Actions actions = new Actions(driver);
            actions.scrollByAmount(0, 400).perform();

            enterProcedureResult(procedureResult);

            actions.scrollByAmount(0, 400).perform();

            enterProcedureDatetime(procedureTime, date, time);

            time = time.plusMinutes(calculateTime(procedure));

            int duration = calculateTime(procedure);

            WebElement durationInput = procedureDuration;
            durationInput.sendKeys(Keys.CONTROL + "a");
            durationInput.sendKeys(Keys.DELETE);
            durationInput.sendKeys(String.valueOf(duration));

            scrollToNextProcedure(i, proceduresList);
        }

        time = time.plusMinutes(5);
        return time;
    }

    public void saveEncounter()
    {
        driver.findElement(NewEncounterPageLocators.saveButtonXpath).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(), 'Взаємодію успішно створено')]"))
        );

//        try {
//            new WebDriverWait(driver, Duration.ofSeconds(5)).until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            By.xpath("//*[contains(text(), 'Взаємодію успішно створено')]"))
//            );
//        } catch (TimeoutException e) {
//            if (driver.findElement(By.xpath("//li[contains(text(), 'General error: 2006')]")).isDisplayed()) {
//                driver.findElement(By.xpath("//button[text()='OK']")).click();
//            }
//
//            WebElement elem = driver.findElement(NewEncounterPageLocators.proceedToSyncButtonXpath);
//            new WebDriverWait(driver, Duration.ofSeconds(5)).until(
//                    ExpectedConditions.elementToBeClickable(
//                            elem))
//            ;
//
//            elem.click();
//
//            driver.findElement(By.xpath("//*[text()='Продовжити']")).click();
//        }

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//*[contains(text(), 'Взаємодію успішно створено')]"))
        );
    }

    public void proceedToSync()
    {
        WebElement elem = driver.findElement(NewEncounterPageLocators.proceedToSyncButtonXpath);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.elementToBeClickable(
                        elem))
        ;

        elem.click();

        driver.findElement(By.xpath("//*[text()='Продовжити']")).click();
    }
}
