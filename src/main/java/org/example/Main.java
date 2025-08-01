package org.example;

import org.example.common.Common;
import org.example.common.DriverInit;
import org.example.common.Entry;
import org.example.common.Strings;
import org.example.frames.EndEpisodeFrame;
import org.example.frames.NewEpisodeFrame;
import org.example.frames.SyncFrame;
import org.example.pages.EncountersPage;
import org.example.pages.EpisodesPage;
import org.example.pages.NewEncounterPage;
import org.example.pages.SearchPersonPage;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        WebDriver driver = new DriverInit().driver;

        SearchPersonPage searchPersonPage = new SearchPersonPage(driver);
        EpisodesPage episodesPage = new EpisodesPage(driver);
        EncountersPage encountersPage = new EncountersPage(driver);
        NewEncounterPage newEncounterPage = new NewEncounterPage(driver);
        SyncFrame syncFrame = new SyncFrame(driver);
        NewEpisodeFrame newEpisodeFrame = new NewEpisodeFrame(driver);
        EndEpisodeFrame endEpisodeFrame = new EndEpisodeFrame(driver);
        Common common = new Common(driver);

        ArrayList<Entry> entries = common.fillEntries("Sheet1");
        LocalTime time = LocalTime.of(13, 5);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));


        for (int i = 0; i < entries.size(); i++) {
            Entry entry = entries.get(i);

            if (i > 0 && ChronoUnit.DAYS.between(entries.get(i - 1).getDate(), entry.getDate()) > 0)
                time = LocalTime.of(10, 0);


            // filling variables from table
            String name = entry.getName();
            String birthdate = common.convertDate(entry.getBirthdate());
            String tooth = entry.getTooth();
            String diagnose = entry.getDiagnose();
            List<String> procedures = Arrays.asList(entry.getProcedures().split(", "));
            LocalDate date = entry.getDate();

            // search for person flow
            System.out.printf("%s %s - started - %s%n", name, tooth, LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            LocalTime startTime = LocalTime.now();
            searchPersonPage.getSearchPage();
            searchPersonPage.enterName(name);
            searchPersonPage.enterBirthdate(birthdate);
            searchPersonPage.submitPersonSearch();
            searchPersonPage.getEpisodesPage();

            // create episode if not found
            try
            {
                episodesPage.searchEpisode2(diagnose, tooth);
            } catch (java.lang.IndexOutOfBoundsException e) {
                episodesPage.getNewEpisodeFrame();
                newEpisodeFrame.enterEpisodeDiagnose(diagnose);
                if (!Arrays.asList(Strings.diagnosesWithoutTooth).contains(diagnose)) {
                    newEpisodeFrame.enterTooth(tooth);
                }
                if (diagnose.equals("Z01.2")) {
                    newEpisodeFrame.changeEpisodeType();
                }

                newEpisodeFrame.enterEpisodeStartDate(date);
                newEpisodeFrame.syncNewEpisode();

                driver.navigate().refresh();
                episodesPage.searchEpisode2(diagnose, tooth);
            }

            // check if encounter is first
            boolean isFirstEncounter = common.noInitialData();
            encountersPage.getNewEncounterPage();

            // new encounter creation flow
            newEncounterPage.enterEncounterDatetime(procedures, date, time);
            newEncounterPage.selectEncounterReason(diagnose);
            newEncounterPage.selectOperation();

            if (!common.isAdult(birthdate))
                newEncounterPage.changePriority();

            if (isFirstEncounter) {
                newEncounterPage.navToDiagnoseTab();
                newEncounterPage.selectNewDiagnose(diagnose);
            }

            if (!procedures.get(0).equals("0")) {
                newEncounterPage.navToProceduresTab();
                time = newEncounterPage.fillInProcedures(procedures, date, time);
            }

            newEncounterPage.saveEncounter();
            newEncounterPage.proceedToSync();

            syncFrame.syncData();

            boolean needToPass = !Arrays.asList(Strings.restorations).contains(procedures.get(procedures.size() - 1)) &&
                    Arrays.asList(Strings.doNotEnd).contains(diagnose);

            if (!needToPass) {
                driver.navigate().back();
                driver.navigate().back();
                driver.navigate().back();
                driver.navigate().refresh();

                episodesPage.getEndEpisodeFrame2(diagnose, tooth);
                endEpisodeFrame.selectEndingReason();
                endEpisodeFrame.enterEndingDate();
                endEpisodeFrame.syncEndingEpisode();

                driver.navigate().refresh();
            }

            System.out.printf("%s %s - finished - %s [next: %s]%n", name, tooth, LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")), time.format(DateTimeFormatter.ofPattern("HH:mm")));

            LocalDateTime endTime = LocalDateTime.now();
            Duration diff = Duration.between(startTime, endTime);
            System.out.println(diff.getSeconds());

        }
//        driver.quit();
    }
}