package com.beymen.tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.beymen.utils.Log;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    public static WebDriver driver;
    
        @BeforeClass
        public static void setup() {
            Log.info("Tests are starting!");
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterClass
    public static void teardown() {
        Log.info("Tests are ending!");
        driver.quit();
    }
}