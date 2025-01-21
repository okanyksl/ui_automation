package com.beymen.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.beymen.utils.Log;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;

    public BasePage (WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    public void waitVisibility(By element) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
        Log.info(element + "Görüntülendi.");
    }

    public void waitForLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };

        wait.until(pageLoadCondition);
    }

    public void click(By element){
        waitVisibility(element);
        driver.findElement(element).click();
        Log.info("Tıklama işlemi gerçekleştirildi.");
    }

     public void setText(By element, String text){
        waitVisibility(element);
        driver.findElement(element).sendKeys(text);
        Log.info("Input alanına " + text +" yazma işlemi gerçekleştirildi.");

    }

    public String getText(By element){
        waitVisibility(element);
        return driver.findElement(element).getText();
    }

    public void keyEnter(By element) {
        Actions actions = new Actions(driver);
        actions.sendKeys(driver.findElement(element), Keys.ENTER).perform();
    }

    public boolean isClicked(By locator) {
        try {
        WebElement element = driver.findElement(locator);
            return element.isSelected();
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isElementVisible(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
    
