package ApiTest;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class RetryListenerImpl implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        if((result.getMethod().getRetryAnalyzer(result))==null){
            result.getMethod().setRetryAnalyzerClass(RetryAnalyser.class);
        }
    }
}