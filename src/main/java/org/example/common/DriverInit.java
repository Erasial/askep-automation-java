package org.example.common;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class DriverInit {

    public final ChromeDriver driver;

    public DriverInit() {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments(Strings.chromeDataDir);
        String seleniumProfile = "--user-data-dir=/home/neo/selenium-chrome-profile";
//        new File("/home/neo/selenium-chrome-profile").mkdirs();
        options.addArguments(seleniumProfile);
        options.addArguments("--start-maximized");
        options.addArguments("disable-infobars");
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "false");
        options.addArguments("--enable-logging", "--v=1");
        driver = new ChromeDriver(options);
    }
}
