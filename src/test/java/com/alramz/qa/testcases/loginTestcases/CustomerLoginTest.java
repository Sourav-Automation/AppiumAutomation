package com.alramz.qa.testcases.loginTestcases;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alramz.qa.base.TestBase;
import com.alramz.qa.pages.LoginPage;
import com.alramz.qa.pages.MyProfilePage;
import com.alramz.qa.utilities.ExcelDataReader;
import com.aventstack.extentreports.Status;

public class CustomerLoginTest extends TestBase {

	@BeforeMethod
	public static void initializeDriver() throws Exception {

		initialization("\\src\\main\\java\\com\\alarmz\\qa\\config\\config.properties");
	}

	LoginPage login;
	MyProfilePage profile;

	String sheetName = "CustomerLogin";
	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir")
			+ "\\src\\main\\java\\com\\alramz\\qa\\sheets\\CustomerLoginTestData.xlsx";

	@DataProvider
	public Object[][] getTestData() {
		// System.out.println("SheetName is: " + sheetName );
		Object data[][] = ExcelDataReader.getTestData(sheetName, TESTDATA_SHEET_PATH);
		// System.out.println("Data is: " + data );
		return data;

	}

	@Test(dataProvider = "getTestData") // Data Provider'
	public void verifyCustomerLoginTest(String runmode, String username, String password) throws Exception {

		test = extent.createTest("Customer Login Test");

		if (runmode.equalsIgnoreCase("YES")) {

			try {

				login = new LoginPage();

				login.clickonSkipButton();
				test.log(Status.INFO, "Clicked on Skip Button");

				login.enterUsername(username);
				test.log(Status.INFO, "Entered Username as : " + username);

				login.goBack();

				login.enterPassword(password);
				test.log(Status.INFO, "Entered Password as : " + password);

				profile = login.clickSignup();
				test.log(Status.INFO, "Clicked on Signin Button");

				login.selectBiometricNo();
				test.log(Status.INFO, "Selected Biometric as No");

				profile.clickonMyProfile();
				test.log(Status.INFO, "Login Successfull");
				test.log(Status.INFO, "Clicked on My Profile Icon");

				profile.selectLogoutOption();
				test.log(Status.INFO, "Selected Logout Option");

				profile.clickonLogoutButton();
				test.log(Status.INFO, "Clicked on Logout Button");
				Thread.sleep(2000);

				if (profile.verifyAlramzLogo().equals(true)) {

					test.log(Status.PASS, "Logout Success");
					Assert.assertTrue(true);
					

				} else {

					test.log(Status.FAIL, "Logout Failed");
					Assert.assertTrue(false);
					
				}

			} catch (Exception e) {

				test.log(Status.FAIL, "Test Failed " + e);
				System.out.println(e);
				Assert.fail();
				extent.flush();
				driver.quit();
			}

		} else {

			test.log(Status.SKIP, "Test Case Skipped ");
			throw new SkipException("Skipped");
		}
	}

	@AfterMethod
	public void logout() {

		extent.flush();
		androiddriver.quit();

	}

}
