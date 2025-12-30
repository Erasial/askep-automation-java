package org.example;

import org.example.common.Common;
import org.example.common.DriverInit;
import org.example.common.Entry;
import org.example.common.PalliativeEntry;
import org.example.frames.SyncFrame;
import org.example.pages.NewEncounterPage;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MobilePalliative {
    public static void main(String[] args) {
        WebDriver driver = new DriverInit().driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        Common common = new Common(driver);
        SyncFrame syncFrame = new SyncFrame(driver);
        NewEncounterPage newEncounterPage = new NewEncounterPage(driver);

        ArrayList<PalliativeEntry> entries = common.palliativeReadTable("palliative");

        LocalDate dateInitial = LocalDate.of(2025, 12, 6);

        LocalDate date = LocalDate.of(2025, 12, 6);

        int offset = 9;

        LocalTime time = LocalTime.of(11, 0).plusMinutes(offset * 30);
        System.out.println(entries.size());
        for (int i = offset; i < entries.size() - 4; i++) { // TODO might wanna change -4
            PalliativeEntry entry = entries.get(i);
            if (i > 5)
                date = date.plusDays(1);
            while (date.getMonthValue() == LocalDate.now().getMonthValue()) {
                System.out.printf("%s -- %s %s\n", entry.getName(), date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), time.format(DateTimeFormatter.ofPattern("HH:mm")));
                driver.get(entry.getRefLink());
                driver.get(entry.getEncounterLink());

                newEncounterPage.changeType();
                newEncounterPage.enterEncounterDatetime(date, time);
                newEncounterPage.navToProceduresTab();
                newEncounterPage.enterProcedureResult();
                newEncounterPage.saveEncounter();
                newEncounterPage.proceedToSync();

                syncFrame.syncData();

                date = date.plusDays(7);
            }

            date = dateInitial;
            time = time.plusMinutes(30);
        }
    }
}