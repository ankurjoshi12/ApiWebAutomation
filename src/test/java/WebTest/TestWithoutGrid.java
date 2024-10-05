package WebTest;

import DriverConfiguration.InitializeDriverSetup;
import Pages.GoogleHomePage;
import TestListners.ReporterTesNg;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;

public class TestWithoutGrid extends InitializeDriverSetup {

    @DataProvider(name = "testDS")
    public Object[][] credentials(){
        return new Object[][]
                {
                        {"firefox"},
                        {"chrome"}
                };
    }

    @Test(groups = {"nogrid"} , dataProvider = "testDS" ,dataProviderClass = TestWithoutGrid.class)
    public void testWithoutGrid(String browser) throws InterruptedException, MalformedURLException {
        InitializeDriverSetup initialiseDriverSetup = new InitializeDriverSetup();
        WebDriver driver = initialiseDriverSetup.initializeDriver(browser);
        Reporter.log("Hitting URL : https://www.google.com");
        driver.get("https://www.google.com");
        log("Taking screen shot" , false);
        GoogleHomePage homePage = new GoogleHomePage(driver);
        Reporter.log("Entering keyword : "+browser);
        homePage.enterKeywordUsingId(browser);
    }

    @Test(groups = {"nogrid1"} , dataProvider = "testDS" ,dataProviderClass = TestWithoutGrid.class)
    public void testWithoutGrid1(String browser) throws InterruptedException, MalformedURLException {
        InitializeDriverSetup initialiseDriverSetup = new InitializeDriverSetup();
        WebDriver driver = initialiseDriverSetup.initializeDriver(browser);
        Reporter.log("Hitting URL : https://www.bigbasket.com");
        driver.get("https://www.bigbasket.com");
        log("Taking screen shot" , false);
//        GoogleHomePage homePage = new GoogleHomePage(driver);
//        Reporter.log("Entering keyword : "+browser);
//        homePage.enterKeywordUsingId("test1");

    }

    @Test
    public void readJSON() throws IOException {
        JSONObject response = new JSONObject(IOUtils.toString(TestWithoutGrid.class.getResourceAsStream("/sample.json"), "UTF-8"));
    }

    @AfterMethod(groups = {"nogrid"})
    public void quiteDriver(){
        if (getDriver().get() != null) {
            // Ensure the WebDriver session is closed
            getDriver().get().quit();
        }
    }
}
