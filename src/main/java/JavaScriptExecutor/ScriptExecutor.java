package JavaScriptExecutor;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ScriptExecutor {
    WebDriver driver ;
    ScriptExecutor(WebDriver driver){
        this.driver = driver ;
    }
    public void executeScript(String script){
        JavascriptExecutor executor = (JavascriptExecutor)driver ;
        executor.executeScript(script);
    }
}
