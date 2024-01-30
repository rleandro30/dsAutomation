package com.datasite.pageObjects;

import com.datasite.componentObjects.TopMenuComponent;
import com.datasite.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BasePage {
    protected WebDriver driver;
    private final WebDriverWait wait;
    protected final Logger logger;


    public BasePage(WebDriver driver, String pageName) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Long.parseLong(ConfigReader.getProperty("waitDuration")));
        this.logger = Logger.getLogger(pageName);
    }
    public void navigateToUrl(String url) {
        try {
            driver.get(url);
            logger.info("Navigated to URL: " + url);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unable to navigate to URL: " + url, e);
        }
    }

    public WebElement waitForElementTobeClickable(WebElement element){
        try {
            return this.wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error waiting for element to be clickable: " + e);
            return null;
        }
    }

    public WebElement waitForElementTobeClickableByXpath(String xpath){
        try {
            return this.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error waiting for element to be clickable by xpath: " + e);
            return null;
        }
    }

    public WebElement waitForElementTobePresentByXpath(String xpath){
        try {
            return this.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error waiting for element to be present by xpath: " + e);
            return null;
        }
    }

    public WebElement waitForElementTobeVisible(WebElement element){
        try {
            return this.wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error waiting for element to be visible: " + e);
        }
        return null;
    }

    public void hoverElement(WebElement element){
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    public void waitForAnyElementVisibility(List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(ConfigReader.getProperty("waitDuration")));
        wait.until(new ExpectedCondition<>() {
            @Override
            public WebElement apply(WebDriver driver) {
                for (WebElement element : elements) {
                    if (element.isDisplayed()) {
                        return element;
                    }
                }
                return null;
            }

            @Override
            public String toString() {
                return "visibility of any element in the list";
            }
        });
    }
    public TopMenuComponent getTopMenu(){
        return new TopMenuComponent(driver);
    }
    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

}