import com.datasite.pageObjects.HomePage;
import com.datasite.pageObjects.ResultsPage;
import com.datasite.utils.ConfigReader;
import com.datasite.utils.Constants;
import com.opencsv.exceptions.CsvValidationException;
import org.testng.annotations.*;
import java.lang.reflect.Method;
import java.io.IOException;
import java.util.*;

import static com.datasite.utils.TestDataUtils.getDataProviderForMethod;

public class FindNearStoreTest extends BaseTest {
    ResultsPage resultsPage;
    String csvPath = Objects.requireNonNull(getClass().getClassLoader().getResource(ConfigReader.getProperty("data.file"))).getPath();

    @DataProvider(name = "searchByLocationProvider")
    public Iterator<Object[]> searchByLocationProvider(Method method) throws IOException, CsvValidationException {
        return getDataProviderForMethod(method, csvPath);
    }

    @DataProvider(name = "searchByLocationAndUnitSizeProvider")
    public Iterator<Object[]> searchByLocationAndUnitSizeProvider(Method method) throws IOException, CsvValidationException {
        return getDataProviderForMethod(method, csvPath);
    }

    @DataProvider(name = "searchByLocationUnitAndTypeProvider")
    public Iterator<Object[]> searchByLocationUnitAndTypeProvider(Method method) throws IOException, CsvValidationException {
        return getDataProviderForMethod(method, csvPath);
    }

    @DataProvider(name = "searchByLocationUnitSizeAndTypeProvider")
    public Iterator<Object[]> searchByLocationUnitSizeAndTypeProvider(Method method) throws IOException, CsvValidationException {
        return getDataProviderForMethod(method, csvPath);
    }

    @Test(dataProvider = "searchByLocationProvider")
    public void searchByLocation(String menuItem1, String subMenuItem1) {
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.getTopMenu().clickOnMenuItem(Constants.TOP_MENU_STORAGE);
        softAssert.assertEquals(homePage.getCurrentUrl(), Constants.STORAGE_URL, "Current URL is not correct");
        homePage.selectItemNearestFromStorageFacility(menuItem1, subMenuItem1);
        resultsPage = homePage.clickOnFindResults();
        resultsPage.waitForPageToLoad();
        softAssert.assertTrue(resultsPage.areAllElementsPresentByLocation(subMenuItem1), "Assertions for results page failed");
        softAssert.assertAll();
    }

    @Test(dataProvider = "searchByLocationAndUnitSizeProvider")
    public void searchByLocationAndUnitSize(String menuItem1, String subMenuItem1, String menuItem2, String subMenuItem2) {
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.getTopMenu().clickOnMenuItem(Constants.TOP_MENU_STORAGE);
        softAssert.assertEquals(homePage.getCurrentUrl(), Constants.STORAGE_URL, "Current URL is not correct");
        homePage.selectItemNearestFromStorageFacility(menuItem1, subMenuItem1);
        homePage.selectItemNearestFromStorageFacility(menuItem2, subMenuItem2);
        resultsPage = homePage.clickOnFindResults();
        resultsPage.waitForPageToLoad();
        softAssert.assertTrue(resultsPage.areAllElementsPresentByLocation(subMenuItem1), "Assertions for results page failed");
        softAssert.assertTrue(resultsPage.isFilterChecked(subMenuItem2), "Filter " + subMenuItem2 + " is not checked");
        softAssert.assertAll();
    }

    @Test(dataProvider = "searchByLocationUnitAndTypeProvider")
    public void searchByLocationUnitAndType(String menuItem1, String subMenuItem1, String menuItem3, String subMenuItem3) {
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.getTopMenu().clickOnMenuItem(Constants.TOP_MENU_STORAGE);
        softAssert.assertEquals(homePage.getCurrentUrl(), Constants.STORAGE_URL, "Current URL is not correct");
        homePage.selectItemNearestFromStorageFacility(menuItem1, subMenuItem1);
        homePage.selectItemNearestFromStorageFacility(menuItem3, subMenuItem3);
        resultsPage = homePage.clickOnFindResults();
        resultsPage.waitForPageToLoad();
        softAssert.assertTrue(resultsPage.areAllElementsPresentByLocation(subMenuItem1), "Assertions for results page failed");
        softAssert.assertTrue(resultsPage.isFilterChecked(subMenuItem3), "Filter " + subMenuItem3 + " is not checked");
        softAssert.assertAll();
    }

    @Test(dataProvider = "searchByLocationUnitSizeAndTypeProvider")
    public void searchByLocationUnitSizeAndType(String menuItem1, String subMenuItem1, String menuItem2, String subMenuItem2, String menuItem3, String subMenuItem3) {
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.getTopMenu().clickOnMenuItem(Constants.TOP_MENU_STORAGE);
        softAssert.assertEquals(homePage.getCurrentUrl(), Constants.STORAGE_URL, "Current URL is not correct");
        homePage.selectItemNearestFromStorageFacility(menuItem1, subMenuItem1);
        homePage.selectItemNearestFromStorageFacility(menuItem2, subMenuItem2);
        homePage.selectItemNearestFromStorageFacility(menuItem3, subMenuItem3);
        resultsPage = homePage.clickOnFindResults();
        resultsPage.waitForPageToLoad();
        softAssert.assertTrue(resultsPage.areAllElementsPresentByLocation(subMenuItem1), "Assertions for results page failed");
        softAssert.assertTrue(resultsPage.isFilterChecked(subMenuItem2), "Filter " + subMenuItem2 + " is not checked");
        softAssert.assertTrue(resultsPage.isFilterChecked(subMenuItem3), "Filter " + subMenuItem3 + " is not checked");
        softAssert.assertAll();
    }
}
