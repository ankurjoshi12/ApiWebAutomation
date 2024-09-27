package Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class GoogleHomePage {
	
	public static Logger logger = Logger.getLogger(GoogleHomePage.class);
	
	WebDriver driver ; 
	WebDriverWait wait ; 

	@FindBy(xpath = "//*[@id=\"APjFqb\"]")
	WebElement searchBox;
	
	@FindBy(id = "APjFqb")
	WebElement searchBoxById;
	
	public GoogleHomePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 60);
		PageFactory.initElements(driver, this);
	}
	
	public void enterKeyword(String keyword){
		logger.info("clicking search box");
		wait.until(ExpectedConditions.elementToBeClickable(searchBox));
		searchBox.click();
		searchBox.sendKeys(keyword);
		logger.info("clicked search box");
	}
	public void enterKeywordUsingId(String keyword){
		logger.info("clicking search box");
		wait.until(ExpectedConditions.elementToBeClickable(searchBoxById));
		searchBoxById.click();
		searchBoxById.sendKeys(keyword);
		logger.info("clicked search box");
	}
}
