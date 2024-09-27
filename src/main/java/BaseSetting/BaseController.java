package BaseSetting;


import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

public class BaseController {
	public static Logger logger = Logger.getLogger(BaseController.class);
	public static String SUITE_NAME = System.getProperty("suiteName");
	public static String SERVER = System.getProperty("serverName");
	public static String BROWSER = System.getProperty("browser");

	public static Boolean screenshot = Boolean.parseBoolean(System.getProperty("screenshot"));
	public static Boolean GRID_MODE =  Boolean.parseBoolean(System.getProperty("grid" , "false"));

	public void takeScreenshot(WebDriver driver){
		TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
		File srcFile  = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String testName = Thread.currentThread().getStackTrace()[2].getMethodName().replace("&", "and");
		String folderName = "/ScreenShots/"+testName ;
		File screenshotDir = new File(System.getProperty("user.dir") + "/" + folderName);
		if (!screenshotDir.exists()) {
			boolean isDirCreated = screenshotDir.mkdirs();
			if (!isDirCreated) {
				System.out.println("Failed to create the directory: " + screenshotDir.getAbsolutePath());
				return;
			}
		}
		File destFile = new File(screenshotDir+"/"+testName+System.currentTimeMillis()+".png");
		try {
			// Save the screenshot to the specified location
			FileHandler.copy(srcFile, destFile);
			System.out.println("Screenshot saved successfully!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
