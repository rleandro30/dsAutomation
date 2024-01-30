package com.datasite.componentObjects;

import com.datasite.pageObjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.logging.Level;

public class TopMenuComponent extends BasePage {
    String topMenuItemXpath = "//a[contains(., '%s') and @class = 'show-for-large']";
    String topSubMenuItemXpath = "//ul[@class = 'sub-menu' and .//a[contains(text(), '%s')]]//a[(.//span[contains(.,'%s')] or contains(.,'%s'))]";
    @FindBy(className = "sub-menu")
    List<WebElement> subMenuItems;

    public TopMenuComponent(WebDriver driver) {
        super(driver, "TopMenuComponent");
        PageFactory.initElements(driver, this);
    }
    public void selectMenuItemAndSubItem(String item, String subItem) {
        hoverMenuItem(item);
        selectSubMenuItem(item,subItem);
    }
    public void clickOnMenuItem(String item) {
        returnMenuItem(item).click();
    }
    private WebElement returnMenuItem(String itemText){
        logger.log(Level.INFO, "Return the menu item: " + itemText);
        String fullXpath = String.format(topMenuItemXpath, itemText);
        logger.log(Level.INFO, "Trying to select item with xpath: " + fullXpath);
        return waitForElementTobePresentByXpath(fullXpath);
    }
    private void hoverMenuItem(String itemText){
        logger.log(Level.INFO, "Hover the menu item: " + itemText);
        WebElement topMenuElement = returnMenuItem(itemText);
        hoverElement(topMenuElement);
        waitForAnyElementVisibility(subMenuItems);
    }
    private void selectSubMenuItem(String itemText, String subItemText){
        logger.log(Level.INFO, "Select the submenu item: " + itemText);
        String fullXpath = String.format(topSubMenuItemXpath, itemText, subItemText, subItemText);
        logger.log(Level.INFO, "Trying to select subitem with xpath: " + fullXpath);
        WebElement topSubMenuElement = waitForElementTobeClickableByXpath(fullXpath);
        topSubMenuElement.click();
    }
}
