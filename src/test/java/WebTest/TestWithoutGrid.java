package WebTest;

import BaseSetting.BaseController;
import DriverConfiguration.InitializeDriverSetup;
import Pages.GoogleHomePage;
import TestListners.ReporterTesNg;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;

public class TestWithoutGrid extends ReporterTesNg {

    @DataProvider(name = "testDS")
    public Object[][] credentials(){
        return new Object[][]
                {
//                        {"firefox"},
                        {"chrome"}
                };
    }

    @Test(groups = {"nogrid"} , dataProvider = "testDS" ,dataProviderClass = TestWithoutGrid.class)
    public void testWithoutGrid(String browser) throws InterruptedException, MalformedURLException {
        InitializeDriverSetup initialiseDriverSetup = new InitializeDriverSetup();
        WebDriver driver = initialiseDriverSetup.initilaizeDriver(browser);
        Reporter.log("Hitting URL : https://www.google.com");
        driver.get("https://www.google.com");
        log("Taking screen shot" , false);
        GoogleHomePage homePage = new GoogleHomePage(driver);
        Reporter.log("Entering keyword : "+browser);
        homePage.enterKeywordUsingId(browser);
        Thread.sleep(10000);
        driver.quit();
    }

    @Test(groups = {"nogrid1"} , dataProvider = "testDS" ,dataProviderClass = TestWithoutGrid.class)
    public void testWithoutGrid1(String browser) throws InterruptedException, MalformedURLException {
        InitializeDriverSetup initialiseDriverSetup = new InitializeDriverSetup();
        WebDriver driver = initialiseDriverSetup.initilaizeDriver(browser);
        Reporter.log("Hitting URL : https://www.google.com");
        driver.get("https://www.google.com");
        takeScreenshot(driver);
        GoogleHomePage homePage = new GoogleHomePage(driver);
        Reporter.log("Entering keyword : "+browser);
        homePage.enterKeywordUsingId("test1");
        Thread.sleep(10000);
        driver.quit();
    }

    @Test
    public void readJSON() throws IOException {
        JSONObject response = new JSONObject(IOUtils.toString(TestWithoutGrid.class.getResourceAsStream("/sample.json"), "UTF-8"));

    }
}
