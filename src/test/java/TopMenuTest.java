import com.datasite.pageObjects.HomePage;
import com.datasite.utils.Constants;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TopMenuTest extends BaseTest {

    @DataProvider(name = "dataProviderMenu")
    public Object[][] dataProviderMenu(){
        return new Object [][]{
                { Constants.TOP_MENU_STORAGE, Constants.SUB_MENU_MOVE_ONLINE_TODAY, Constants.MOVE_ONLINE_TODAY_URL },
                { Constants.TOP_MENU_TRAILERS_TOWING, Constants.SUB_MENU_FAQS, Constants.FAQS_URL },
                { Constants.TOP_MENU_STORAGE, Constants.SUB_MENU_FAQS, Constants.STORAGE_FAQS_URL }
        };
    }

    @Test(dataProvider = "dataProviderMenu")
    public void openTopMenuSubItem(String topMenuItem, String topSubMenuItem, String expectedUrl) {
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.getTopMenu().selectMenuItemAndSubItem(topMenuItem, topSubMenuItem);
        Assert.assertEquals(homePage.getCurrentUrl(), expectedUrl);
    }
}
