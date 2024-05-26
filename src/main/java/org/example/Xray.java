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
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Xray {

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
        ArrayList<Entry> entries = common.fillEntries("xray");
        LocalTime time = LocalTime.of(10, 0); // TODO change for variables

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        for (int i = 0; i < entries.size(); i++) {
            Entry entry = entries.get(i);

            if (i > 0 && ChronoUnit.DAYS.between(entries.get(i - 1).getDate(), entry.getDate()) > 0) {
                time = LocalTime.of(10, 0);
            }

            String name = entry.getName();
            String birthdate = common.convertDate(entry.getBirthdate());
            String tooth = entry.getTooth();
            String diagnose = entry.getDiagnose();
            List<String> procedures = Arrays.asList(entry.getProcedures().split(", "));
            LocalDate date = entry.getDate();

            // search person
            searchPersonPage.getSearchPage();
            searchPersonPage.enterName(name);
            searchPersonPage.enterBirthdate(birthdate);
            searchPersonPage.submitPersonSearch();
            searchPersonPage.getEpisodesPage();

            // search episode
            episodesPage.expandFilter();
            episodesPage.clearEpisodeName();
            episodesPage.enterEpisodeName(diagnose, tooth);
            episodesPage.submitEpisodeSearch();

            // create new episode if it doesn't already exist
            if (common.noInitialData()) {
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
                episodesPage.expandFilter();
                episodesPage.clearEpisodeName();
                episodesPage.submitEpisodeSearch();
            }

            episodesPage.openDropdownMenu();
            episodesPage.getEncountersPage();
            boolean isFirstEncounter = common.noInitialData();
            encountersPage.getNewEncounterPage();

            // fill encounter data
            newEncounterPage.enterEncounterDatetime(procedures, date, time);
            newEncounterPage.selectEncounterReason(diagnose);
            newEncounterPage.selectOperation();
            if (!common.isAdult(birthdate)) {
                newEncounterPage.changePriority();
            }

            if (isFirstEncounter) {
                newEncounterPage.navToDiagnoseTab();
                newEncounterPage.selectNewDiagnose(diagnose);
            }

            newEncounterPage.saveEncounter();
            newEncounterPage.proceedToSync();

            syncFrame.syncData();


        }
        driver.quit();
    }
}
