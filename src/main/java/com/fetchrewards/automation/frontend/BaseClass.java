package com.fetchrewards.automation.frontend;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Base Class includes basic config
 * Currently only set chrome browser as target browser
 *
 * @author Ning Yuan.
 */

public class BaseClass {
    public static WebDriver driver;
    public static WebDriverWait wait;

    static Properties defaultConfig = new Properties();
    static {
        try {
            defaultConfig.load(BaseClass.class.getClassLoader().getResourceAsStream("config.properties"));

        } catch (IOException e) {
            Assert.fail("BaseClass static block Could not load a property file");
        }
    }


    public static void instantiateDriver(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("enable-automation");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-browser-side-navigation");
        options.addArguments("--disable-gpu");
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        driver.get(defaultConfig.getProperty("url"));
    }

}
