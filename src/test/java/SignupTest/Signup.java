package SignupTest;

import BaseSetting.BaseController;
import DriverConfiguration.InitializeDriverSetup;
import Pages.HomePage;
import Pages.SignupPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Signup extends BaseController {

    @Test(groups = {"signup"})
    public void SignUpClient(){
        InitializeDriverSetup intialiseDriver = new InitializeDriverSetup();
        WebDriver driver = intialiseDriver.getDriver("chrome");
        driver.get(SERVER);
        HomePage homePage = new HomePage(driver);
        homePage.clickSignup().clickBusinessModulde();

        SignupPage page = new SignupPage(driver);
        page.enterFirstName("Ankur").enterLastName("Joshi").enterPhoneNumber("7022243452")
                .enterCompanyName("InstaWork").enterWorkEmail("xyz@gmail.com").clickSignup();
        Assert.assertEquals(driver.getTitle() ,"welcome");
    }

}
