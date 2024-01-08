package com.alramz.qa.extentReports;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;
import com.alramz.qa.base.TestBase;
import com.alramz.qa.utilities.TakeScreenshot;
import com.aventstack.extentreports.Status;

public class ExtentReportListener extends TestBase implements ITestListener {
	
	TakeScreenshot screenshot = new TakeScreenshot();

	@Override
	public void onTestSuccess(ITestResult result) {

		test.log(Status.PASS, "Test Passed Successfully");

	}

	@Override
	public void onTestFailure(ITestResult result) {

		test.fail(result.getThrowable());
		String filepath = null;
		try {
			filepath = screenshot.getScreenshot(result.getMethod().getMethodName());
			test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		// TEST SKIP CODE
		if (result.wasRetried()) {
			test.info("***** Retried " + result.getName() + " test has failed *****");
			String filepath = null;
			try {
				filepath = screenshot.getScreenshot(result.getMethod().getMethodName());
				test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			test.skip("Test Case Retried");
		} else {
			
			test.log(Status.SKIP, "Test Case Skipped");

		}

	}

}
