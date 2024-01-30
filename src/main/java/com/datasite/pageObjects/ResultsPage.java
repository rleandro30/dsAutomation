package com.datasite.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Level;

public class ResultsPage extends BasePage {

    String xpathCheckedFilter = "//li[.//input[contains(@value, '%s')]]//input[@checked = 'checked']//parent::label";
    @FindBy(id = "resultFilters")
    private WebElement resultsFilters;
    @FindBy(id = "movingFromInput")
    private WebElement movingFromInput;
    String pageHeaderXpath = "//h1[contains(text(), '%s')]";
    public ResultsPage(WebDriver driver){
        super(driver, "ResultsPage");
        PageFactory.initElements(driver, this);
    }
    public void waitForPageToLoad(){
        waitForElementTobeVisible(resultsFilters);
    }
    public boolean areAllElementsPresentByLocation(String location){
        boolean correctUrl = driver.getCurrentUrl().contains(location.replaceAll(",\\s", "-"));
        logger.log(Level.INFO, "The URL is correct: " + correctUrl);
        boolean correctHeader = waitForElementTobePresentByXpath(String.format(pageHeaderXpath, location)).isDisplayed();
        logger.log(Level.INFO, "The header is correct: " + correctHeader);
        boolean correctInputText= waitForElementTobeVisible(movingFromInput).getAttribute("value").contains(location);
        logger.log(Level.INFO, "The input text has the correct location: " + correctInputText);
        return  correctUrl && correctHeader && correctInputText;
    }
    public boolean isFilterChecked(String filterValue){
        String fullXpath = String.format(xpathCheckedFilter, filterValue);
        logger.log(Level.INFO, "Checking visibility of filter: " + fullXpath);
        return waitForElementTobePresentByXpath(fullXpath).isDisplayed();
    }
}
