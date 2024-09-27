package Pages;

import BaseSetting.BaseController;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupPage extends BaseController {
    WebDriver driver ;
    WebDriverWait wait ;
    @FindBy(xpath = "//*[@id=\"id_given_name\"]")
    WebElement firstName;
    @FindBy(xpath = "//*[@id=\"id_family_name\"]")
    WebElement lastName;
    @FindBy(xpath = "//*[@id=\"id_company_name\"]")
    WebElement company;
    @FindBy(xpath = "//*[@id=\"id_phone_number\"]")
    WebElement phoneNumber;
    @FindBy(xpath = "//*[@id=\"id_email\"]")
    WebElement workEmail;
    @FindBy(xpath = "//*[@id=\"id_password\"]")
    WebElement password;

    @FindBy(xpath = "//*[@id=\"business-signup\"]")
    WebElement signUpButton ;


    public SignupPage(WebDriver driver ){
        wait = new WebDriverWait(driver , 30);
        PageFactory.initElements(driver , this );
    }

    public SignupPage enterFirstName(String firstNameText){
        wait.until(ExpectedConditions.elementToBeClickable(firstName));
        firstName.clear();
        firstName.sendKeys(firstNameText);
        return this ;
    }
    public SignupPage enterLastName(String lastNameText){
        wait.until(ExpectedConditions.elementToBeClickable(lastName));
        lastName.clear();
        lastName.sendKeys(lastNameText);
        return this ;
    }

    /**
     *
     * @param companyName
     */
    public SignupPage enterCompanyName(String companyName){
        wait.until(ExpectedConditions.elementToBeClickable(company));
        company.clear();
        company.sendKeys(companyName);
        return this ;
    }
    public SignupPage enterPhoneNumber(String phoneNumberText){
        wait.until(ExpectedConditions.elementToBeClickable(phoneNumber));
        phoneNumber.clear();
        phoneNumber.sendKeys(phoneNumberText);
        return this ;
    }
    public SignupPage enterWorkEmail(String email){
        wait.until(ExpectedConditions.elementToBeClickable(workEmail));
        workEmail.clear();
        workEmail.sendKeys(email);
        return this ;
    }

    public SignupPage enterPassword(String pass){
        wait.until(ExpectedConditions.elementToBeClickable(password));
        password.clear();
        password.sendKeys(pass);
        return this ;
    }
    public void clickSignup(){
        wait.until(ExpectedConditions.elementToBeClickable(signUpButton));
        signUpButton.click();
    }


}
