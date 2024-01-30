package com.datasite.pageObjects;

import com.datasite.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage  extends  BasePage{

    String xpathFiltersCheckBox = "//li[.//input[contains(@value, '%s')]]";
    @FindBy(id = "movingFromInput")
    private WebElement locationInput;
    @FindBy(className = "ui-menu-item")
    private WebElement subMenuOption;
    @FindBy(id = "selectboxUnitSizeInput")
    private WebElement unitSize;
    @FindBy(id = "selectboxInput")
    private WebElement storageType;
    @FindBy(id = "storageUnitSizes")
    private WebElement unitSizeFilters;
    @FindBy(id = "storageFilters")
    private WebElement storageTypeFilters;
    @FindBy(xpath = "//button[normalize-space(text())='Find Storage']")
    private WebElement findStorageButton;

    public HomePage(WebDriver driver) {
        super(driver, "HomePage");
        PageFactory.initElements(driver, this);
    }

    public void selectItemNearestFromStorageFacility(String menuItem, String subMenuItem){
        switch (menuItem){
            case "location":
                waitForElementTobeClickable(locationInput);
                locationInput.click();
                enterLocation(subMenuItem);
                break;
            case "unitSize":
                waitForElementTobeClickable(unitSize);
                unitSize.click();
                waitForElementTobeVisible(unitSizeFilters);
                selectFilter(subMenuItem);
                unitSize.click();
                break;
            case "storageType":
                waitForElementTobeClickable(storageType);
                storageType.click();
                waitForElementTobeVisible(storageTypeFilters);
                selectFilter(subMenuItem);
                unitSize.click();
                break;
            default:
                break;
        }
    }

    public void openHomePage(){
        navigateToUrl(ConfigReader.getProperty("homePage.url"));
    }

    private void enterLocation(String subMenuItem){
        locationInput.sendKeys(subMenuItem);
        waitForElementTobeClickable(subMenuOption).click();
    }
    public ResultsPage clickOnFindResults(){
        findStorageButton.click();
        return new ResultsPage(driver);
    }
    public void selectFilter(String filterOption){
        String fullXpath = String.format(xpathFiltersCheckBox, filterOption);
        waitForElementTobePresentByXpath(fullXpath).click();
    }
}
