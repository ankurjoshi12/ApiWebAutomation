package TestListners;

import BaseSetting.BaseController;
import DriverConfiguration.InitializeDriverSetup;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ReporterTesNg extends InitializeDriverSetup {
    public static Logger logger = Logger.getLogger(ReporterTesNg.class);
    public void log(String step , boolean result) {
        logger.info(step);
        Reporter.log(step);
//        addTestcaseStepRow(step, result);
    }

//    public static void addTestcaseStepRow(String step, boolean result){
////        Reporter
//        if(screenshot && !result) {
//            TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
//            File srcFile  = takesScreenshot.getScreenshotAs(OutputType.FILE);
//            String testName = Thread.currentThread().getStackTrace()[2].getMethodName().replace("&", "and");
//            String folderName = "/ScreenShots/"+testName ;
//            File screenshotDir = new File(System.getProperty("user.dir") + "/" + folderName);
//            if (!screenshotDir.exists()) {
//                boolean isDirCreated = screenshotDir.mkdirs();
//                if (!isDirCreated) {
//                    System.out.println("Failed to create the directory: " + screenshotDir.getAbsolutePath());
//                    return;
//                }
//            }
//            File destFile = new File(screenshotDir+"/"+testName+System.currentTimeMillis()+".png");
//            try {
//                // Save the screenshot to the specified location
//                FileHandler.copy(srcFile, destFile);
//                System.out.println("Screenshot saved successfully!");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
}
