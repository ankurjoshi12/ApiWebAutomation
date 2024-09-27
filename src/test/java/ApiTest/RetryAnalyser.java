package ApiTest;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyser implements IRetryAnalyzer {
    int retry_count = 0 ;
     final static  int max_retry = 2 ;
     public boolean retry(ITestResult result){
        if(retry_count<max_retry){
            retry_count++ ;
            return true ;
        }
        return false;
     }

}
