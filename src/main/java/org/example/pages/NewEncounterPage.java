package org.example.pages;

import org.example.common.Strings;
import org.example.locators.NewEncounterPageLocators;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;


public class NewEncounterPage extends BasePage {


    public NewEncounterPage(WebDriver driver) {
        super(driver);
    }

    private int calculateTime(String procedure) {
        if (Arrays.asList(Strings.min5).contains(procedure))
            return 5;
        else if (Arrays.asList(Strings.min10).contains(procedure))
            return 10;
        else if (Arrays.asList(Strings.min20).contains(procedure))
            return 20;
        else
            return 30;
    }

    private void scrollToNextProcedure(int i, List<String> proceduresList)
    {
        if (i < proceduresList.size() - 1) {
            Actions actions = new Actions(driver);
            actions.scrollByAmount(0, 800).perform();
            driver.findElement(NewEncounterPageLocators.addProcedureButtonXpath).click();

            waitClickable(By.xpath("//span[contains(text(), 'Процедура №" + (i + 2) + "')]"));

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
        waitVisible(NewEncounterPageLocators.procedureSuccessButtonXpath);

        common.findLast(NewEncounterPageLocators.procedureSuccessButtonXpath).click();
    }

    public void enterEncounterDatetime( List<String> proceduresList, LocalDate date, LocalTime time)
    {
        WebElement datetimeInput = driver.findElement(NewEncounterPageLocators.dateInputId);
        String rightDate = common.convertDate(date.toString()).replace(".", "") + time.toString().replace(":", "");
        datetimeInput.sendKeys(rightDate);

        int duration = 0;

        for (String procedure : proceduresList) {
            if (Arrays.asList(Strings.min5).contains(procedure))
                duration += 5;
            else if (Arrays.asList(Strings.min10).contains(procedure))
                duration += 10;
            else if (Arrays.asList(Strings.min20).contains(procedure))
                duration += 20;
            else
                duration += 30;
        }

        driver.findElement(NewEncounterPageLocators.durationInputId).click();
        driver.findElement(NewEncounterPageLocators.durationInputId).sendKeys(String.valueOf(duration));

    }

    public void enterEncounterDatetime(LocalDate date, LocalTime time)
    {
        WebElement datetimeInput = driver.findElement(NewEncounterPageLocators.dateInputId);
        String rightDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String rightTime = time.format(DateTimeFormatter.ofPattern("hh:mm"));

        datetimeInput.sendKeys(rightDate + " " + rightTime);

        int duration = 20;

        driver.findElement(NewEncounterPageLocators.durationInputId).click();
        driver.findElement(NewEncounterPageLocators.durationInputId).sendKeys(String.valueOf(duration));

    }

    public void selectEncounterReason(String diagnose)
    {
        String reason;
        if (diagnose.equals("Z01.2"))
            reason = "D31";
        else
            reason = "D82";

        driver.findElement(NewEncounterPageLocators.reasonInputId).sendKeys(reason);
        driver.findElement(By.xpath("//span[contains(text(), '" + reason + "')]")).click();
        driver.findElement(NewEncounterPageLocators.reasonInputId).sendKeys(Keys.ESCAPE);
    }

    public void selectOperation()
    {
        driver.findElement(NewEncounterPageLocators.operationInputId).sendKeys("D67007");
        waitVisible(By.xpath("//span[contains(text(), '" + "D67007" + "')]")).click();
        driver.findElement(NewEncounterPageLocators.operationInputId).sendKeys(Keys.ESCAPE);
    }

    public void enterProcedureResult()
    {
        WebElement procedureResult = driver.findElement(By.xpath("//div[text()='Результат проведення процедури']/..//input[@placeholder='Оберіть']"));
        procedureResult.click();
        waitVisible(NewEncounterPageLocators.procedureSuccessButtonXpath);

        common.findLast(NewEncounterPageLocators.procedureSuccessButtonXpath).click();
    }

    public void changePriority()
    {
        Actions actions = new Actions(driver);
        actions.scrollByAmount(0, 200).perform();

        driver.findElement(NewEncounterPageLocators.prioritySelectId).click();
        driver.findElement(By.xpath("//div[contains(text(), 'Плановий')]")).click();
    }

    public void changeType()
    {
        driver.findElement(NewEncounterPageLocators.typeSelectId).click();
        driver.findElement(By.xpath("//div[contains(text(), 'за місцем')]")).click();
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
        waitClickable(NewEncounterPageLocators.diagnoseTypeSelectId).click();
        driver.findElement(NewEncounterPageLocators.newDiagnoseSelectXpath).click();

        driver.findElement(NewEncounterPageLocators.newDiagnoseInputXpath).sendKeys(diagnose);
        driver.findElement(By.xpath("//span[contains(text(), '" + diagnose + "')]")).click();

        Actions actions = new Actions(driver);
        actions.scrollByAmount(0, 100).perform();
    }

    public void navToProceduresTab()
    {
        WebElement proceduresTab = driver.findElement(NewEncounterPageLocators.proceduresTabLinkXpath);
        Actions actions = new Actions(driver);
        actions.scrollToElement(proceduresTab).perform();
//        driver.findElements(By.xpath("//i[@class='v-icon notranslate mdi mdi-chevron-right theme--light']")).get(2).click();
        proceduresTab.click();
    }

    public LocalTime fillInProcedures(List<String> proceduresList, LocalDate date, LocalTime time)
    {
        for (int i = 0; i < proceduresList.size(); i++) {
            WebElement procedureInput, procedureResult, procedureTime, procedureDuration;
            waitVisible(By.xpath("//div[text()='Результат проведення процедури']/..//input[@placeholder='Оберіть']"));

            procedureInput = driver.findElements(By.xpath("//div[text()='Медична послуга']/../..//input[@placeholder='Оберіть']")).get(i);
            procedureResult = driver.findElement(By.xpath("//div[text()='Результат проведення процедури']/..//input[@placeholder='Оберіть']"));
            procedureTime = driver.findElements(By.xpath("//div[contains(text(), 'Період проведення (від)')]/..//input")).get(i);
            procedureDuration = driver.findElements(By.xpath("//div[text()='Тривалість']/..//input")).get(i + 1);

            String procedure = proceduresList.get(i);
            if (procedure.equals("97322"))
                procedure = "97322-00";

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
        waitVisible(By.xpath("//*[contains(text(), 'Взаємодію успішно створено')]"));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        waitInvisible(By.xpath("//*[contains(text(), 'Взаємодію успішно створено')]"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }

    public void proceedToSync()
    {
        waitClickable(NewEncounterPageLocators.proceedToSyncButtonXpath).click();
//        driver.findElement(By.xpath("//*[text()='Продовжити']")).click();
    }
}
