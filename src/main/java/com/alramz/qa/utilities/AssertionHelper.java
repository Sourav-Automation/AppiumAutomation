package com.alramz.qa.utilities;

import org.testng.asserts.SoftAssert;

import com.alramz.qa.base.TestBase;
import com.aventstack.extentreports.Status;

public class AssertionHelper extends TestBase {

	public static void verifyText(String actualResults, String expectedResults, String message) {
		SoftAssert softassert = new SoftAssert();
		test.log(Status.INFO, "Actual Results : " + actualResults + "   Expected Results : " + expectedResults);
		softassert.assertEquals(actualResults, expectedResults, message);
		softassert.assertAll();

	}

	public static void verifyTrue(boolean status, String message) {

		SoftAssert softassert = new SoftAssert();
		softassert.assertTrue(status, message);
		softassert.assertAll();

	}
}
