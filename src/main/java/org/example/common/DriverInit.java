package org.example.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverInit {

    public ChromeDriver driver;

    public DriverInit() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(Strings.chromeDataDir);
        options.addArguments("--start-maximized");
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
    }
}
