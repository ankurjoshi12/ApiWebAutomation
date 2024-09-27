package Pages;

import BaseSetting.BaseController;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BaseController {
    WebDriver driver ;
    WebDriverWait wait ;
    @FindBy(xpath = "//*[@id=\"navSignUpModalLabel\"]")
    WebElement signUpButton;

    @FindBy(xpath = "//*[@id=\"navSignUpModal\"]/div/div/div/a[1]")
    WebElement businessModuleElement;

    public HomePage(WebDriver driver ){
        wait = new WebDriverWait(driver ,30);
        PageFactory.initElements(driver , this );
    }

    public HomePage clickSignup(){
    wait.until(ExpectedConditions.elementToBeClickable(signUpButton));
    signUpButton.click();
    return this ;
    }

    public void clickBusinessModulde(){
        wait.until(ExpectedConditions.elementToBeClickable(businessModuleElement));
        businessModuleElement.click();
    }



}
