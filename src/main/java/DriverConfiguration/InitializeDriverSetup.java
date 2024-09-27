package DriverConfiguration;

import BaseSetting.BaseController;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class InitializeDriverSetup extends BaseController {
    public static WebDriver driver ;

    public void log(String step, boolean result) {
        logger.info(step);
        Date date = new Date();
        loggingAndScreenshot(step, date, result ? "Pass" : "Fail", screenshot);
    }

    public static void loggingAndScreenshot(String step, Date date, String result , boolean screenshot){
//        Reporter
        if(screenshot){
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
    public WebDriver initilaizeDriver(String browserName) throws MalformedURLException {
        if(GRID_MODE){
            driver = new RemoteWebDriver(new URL("http://192.168.1.7:4444"),getCapability(browserName));
            return driver ;
        }
        else{
            driver  = getDriver(BROWSER);
            return driver ;
        }
    }
    public static DesiredCapabilities getCapability(){
        DesiredCapabilities capability = new DesiredCapabilities();
        HashMap<String , List> options = new HashMap<>();
        switch(BROWSER){
            case "firefox":
                capability = DesiredCapabilities.firefox();
                options.put("args" ,Arrays.asList("--disable-gpu", "--window-size=1920,1080") );
                capability.setCapability("moz:firefoxOptions", options);
                break ;
            case "chrome":
                capability = DesiredCapabilities.chrome();
                options.put("args" ,Arrays.asList("--disable-gpu", "--window-size=1920,1080") );
//                "--headless",
                capability.setCapability("goog:chromeOptions", options);
                break ;
            default:
                capability = DesiredCapabilities.chrome();
                break ;
        }
        return capability ;
    }
    public static DesiredCapabilities getCapability(String browserName){
        DesiredCapabilities capability = new DesiredCapabilities();
        HashMap<String , List> options = new HashMap<>();
        switch(browserName){
            case "firefox":
                capability = DesiredCapabilities.firefox();
                options.put("args" ,Arrays.asList("--disable-gpu", "--window-size=1920,1080") );
                capability.setCapability("moz:firefoxOptions", options);
                break ;
            case "chrome":
                capability = DesiredCapabilities.chrome();
                options.put("args" ,Arrays.asList( "--disable-gpu", "--window-size=1920,1080") );
//                "--headless",
                capability.setCapability("goog:chromeOptions", options);
                break ;
            default:
                capability = DesiredCapabilities.chrome();
                break ;
        }
        return capability ;
    }
    public static FirefoxOptions firefoxOptionsSetup(){
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        return options ;
    }

    public static ChromeOptions chromeOptionsSetup(){
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        return options ;
    }

    public WebDriver getDriver(String browser){
        if(browser.equalsIgnoreCase("firefox")){
            startGeckoDriver();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setHeadless(true);
            firefoxOptions.setBinary("/Applications/Firefox.app/Contents/MacOS/firefox");
            return new FirefoxDriver(firefoxOptions);
        }
        else if(browser.equalsIgnoreCase("chrome")){
            startChromeDriver();
            ChromeOptions chromeOptions = new ChromeOptions();
//            chromeOptions.setHeadless(true);
            return new ChromeDriver(chromeOptions);
        }
        else{
            startChromeDriver();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("headless");
            return new ChromeDriver(chromeOptions);
        }
    }
    public static void startGeckoDriver(){
         WebDriverManager.firefoxdriver().setup();
    }

    public static void startChromeDriver() {
         WebDriverManager.chromedriver().setup();
    }
}
