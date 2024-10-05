package DriverConfiguration;

import BaseSetting.BaseController;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class InitializeDriverSetup extends BaseController {



    // ThreadLocal to ensure each thread gets its own WebDriver instance
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
//    public static WebDriver driver ;
    public static WebDriver driverWithImplicitWait ;
    public static WebDriverWait wait ;
    public static Wait<WebDriver> fluentWait ;

    public static ThreadLocal<WebDriver> getDriver() {
        return driver;
    }
    public void log(String step, boolean result) {
        logger.info(step);
        Date date = new Date();
        loggingAndScreenshot(step, date, result ? "Pass" : "Fail", screenshot);
    }

    public static void loggingAndScreenshot(String step, Date date, String result , boolean screenshot){
//        Reporter
        if(screenshot){
            TakesScreenshot takesScreenshot = (TakesScreenshot)driver.get();
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
    public WebDriver initializeDriver(String browserName) throws MalformedURLException {
        if(GRID_MODE){
//            driver.set(new RemoteWebDriver(new URL("http://192.168.1.4:4444"),getCapability(browserName)));
            driver.set(new RemoteWebDriver(new URL("http://host.docker.internal:4444/wd/hub"), getCapability(browserName)));

            // Implicit wait
//            driverWithImplicitWait = getDriver(BROWSER);
//            driverWithImplicitWait.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

            //Explicit wait
//            wait = new WebDriverWait(driver , Duration.ofSeconds(30));
//            wait.pollingEvery(Duration.ofSeconds(1));

            //Fluent Wait
//            fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
//                    .pollingEvery(Duration.ofSeconds(2)).ignoring(ElementNotInteractableException.class);

            return driver.get();
        }
        else{
            driver  = (ThreadLocal<WebDriver>) getDriver(BROWSER);
            return (WebDriver) driver;
        }
    }
    public static DesiredCapabilities getCapability(){
        DesiredCapabilities capability = new DesiredCapabilities();
        HashMap<String , List> options = new HashMap<>();
        switch(BROWSER){
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions().addArguments("--disable-gpu").addArguments("--window-size=1920,1080")
                        .addArguments("--headless");
                capability.setCapability("moz:firefoxOptions", firefoxOptions);
//                capability = DesiredCapabilities.firefox();
//                options.put("args" ,Arrays.asList("--disable-gpu", "--window-size=1920,1080") );
//                capability.setCapability("moz:firefoxOptions", options);
                break ;
            case "chrome":
                ChromeOptions chromeoptions = new ChromeOptions().addArguments("--disable-gpu").addArguments("--window-size=1920,1080")
                        .addArguments("--headless");
                capability.setCapability("goog:chromeOptions", chromeoptions);
//                capability = DesiredCapabilities.chrome();
//                options.put("args" ,Arrays.asList("--disable-gpu", "--window-size=1920,1080") );
////                "--headless",
//                capability.setCapability("goog:chromeOptions", options);
                break ;
//            default:
////                capability = DesiredCapabilities.chrome();
//                ChromeOptions chromeOptions = new ChromeOptions().addArguments("--disable-gpu").addArguments("--window-size=1920,1080")
//                        .addArguments("--headless");
//                capability.setCapability("goog:chromeOptions", chromeOptions);
//                break ;
        }
        return capability ;
    }
    public static DesiredCapabilities getCapability(String browserName){
        DesiredCapabilities capability = new DesiredCapabilities();
        switch(browserName){
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions().addArguments("--disable-gpu")
                        .addArguments("--window-size=1920,1080");
                firefoxOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
                firefoxOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS_AND_NOTIFY);
//                            .addArguments("--headless");
                capability.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
//                capability = DesiredCapabilities.firefox();
//                options.put("args" ,Arrays.asList("--disable-gpu", "--window-size=1920,1080") );
//                capability.setCapability("moz:firefoxOptions", options);
                break ;
            case "chrome":
                ChromeOptions chromeoptions = new ChromeOptions().addArguments("--disable-gpu")
                        .addArguments("--window-size=1920,1080");
//                        .addArguments("--headless");
                chromeoptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
                chromeoptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS_AND_NOTIFY);
//                capability.setCapability("goog:chromeOptions", chromeoptions);
                capability.setCapability(ChromeOptions.CAPABILITY, chromeoptions);
                break ;
            default:
//                ChromeOptions chromeOptions = new ChromeOptions().addArguments("--disable-gpu").addArguments("--window-size=1920,1080")
//                        .addArguments("--headless");
//                capability.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
//                break ;
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
