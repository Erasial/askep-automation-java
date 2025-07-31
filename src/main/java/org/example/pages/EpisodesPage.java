package org.example.pages;

import org.example.common.Strings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.example.locators.EpisodesPageLocators;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class EpisodesPage {
    private final WebDriver driver;

    public EpisodesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void getNewEpisodeFrame() {
        driver.findElement(EpisodesPageLocators.getNewEpisodeFrameButtonClass).click();
    }

    public void searchEpisode2 (String diagnose, String tooth)
    {
        String episodeName;
        if (diagnose.equals("Z01.2") || diagnose.equals("K03.6"))
        {
            episodeName = Strings.diagnoseFullName.get(diagnose);
        } else {
            episodeName = " - " + tooth;
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        List<WebElement> td = driver.findElements(By.xpath("//td[@class='text-start']"));
        int i = 0;
        for (; i < td.size(); i++) {
            if (td.get(i).getText().contains(episodeName))
            {
                break;
            }
        }

        int index = (i + 4) / 7;
        List<WebElement> list = driver.findElements(By.xpath("//i[@class='v-icon notranslate mdi mdi-dots-vertical theme--light']"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        list.get(index).click();
        driver.get(
                Objects.requireNonNull(driver.findElement(By.xpath("//a[@class='my-1 justify-start v-btn v-btn--is-elevated v-btn--has-bg theme--light v-size--small primary']"))
                        .getAttribute("href"))
        );

    }

    public void getEndEpisodeFrame2(String diagnose, String tooth)
    {
        String episodeName;
        if (diagnose.equals("Z01.2") || diagnose.equals("K03.6"))
        {
            episodeName = Strings.diagnoseFullName.get(diagnose);
        } else {
            episodeName = " - " + tooth;
        }

        List<WebElement> td = driver.findElements(By.xpath("//td[@class='text-start']"));
        int i = 0;
        for (; i < td.size(); i++) {
            if (td.get(i).getText().contains(episodeName))
            {
                break;
            }
        }

        int index = (i + 4) / 7;
        List<WebElement> menuList = driver.findElements(By.xpath("//i[@class='v-icon notranslate mdi mdi-dots-vertical theme--light']"));
        menuList.get(index).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Завершити епізод')]")).click();
    }
}
