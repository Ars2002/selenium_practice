package retryTestCases;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer{

	int retryCount = 0;
	int maxRetry = 3;
	
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(!result.isSuccess() && retryCount<maxRetry) {
			retryCount++;
			return true;
		}
		return false;
	}

}
