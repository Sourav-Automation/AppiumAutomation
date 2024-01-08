package com.alramz.qa.testcases.watchlistTestcases;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alramz.qa.base.TestBase;
import com.alramz.qa.pages.LoginPage;
import com.alramz.qa.pages.WatchlistPage;
import com.alramz.qa.utilities.ExcelDataReader;
import com.aventstack.extentreports.Status;

public class CreateNewWatchlistTest extends TestBase {

	@BeforeMethod
	public static void initializeDriver() throws Exception {

		initialization("\\src\\main\\java\\com\\alarmz\\qa\\config\\config.properties");
	}

	LoginPage login;
	WatchlistPage watchlist;

	String sheetName = "CreateWatchlist";
	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir")
			+ "\\src\\main\\java\\com\\alramz\\qa\\sheets\\CreateWatchlistTestData.xlsx";

	@DataProvider
	public Object[][] getTestData() {
		// System.out.println("SheetName is: " + sheetName );
		Object data[][] = ExcelDataReader.getTestData(sheetName, TESTDATA_SHEET_PATH);
		// System.out.println("Data is: " + data );
		return data;

	}

	@Test(dataProvider = "getTestData") // Data Provider'
	public void createNewWatchlistVerificationTest(String runmode, String username, String password,
			String watchlistName) throws Exception {

		test = extent.createTest("Create New Watchlist Verification Test");

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

				watchlist = login.clickonSigninButton();
				test.log(Status.INFO, "Clicked on Signin Button");
				Thread.sleep(20000);

				watchlist.selectBiometricNo();
				test.log(Status.INFO, "Selected Biometric as No");
				Thread.sleep(3000);

				watchlist.tapByCoordinates(170, 362);
				test.log(Status.INFO, "Clicked on WatchList Button");
				Thread.sleep(3000);

				watchlist.clickonWatchlistName();
				test.log(Status.INFO, "Clicked on Watchlist Field");

				watchlist.enterWatchlistName(watchlistName);
				test.log(Status.INFO, "Entered Watchlist Name as: " + watchlistName);

				watchlist.clickonCreateButton();
				test.log(Status.INFO, "Clicked on Create Button");
				Thread.sleep(3000);

				if (watchlist.getSuccessMessage().contains("Watchlist is Created")) {

					test.log(Status.PASS, "Watchlist Created Successfully");
					Assert.assertTrue(true);

				} else {

					test.log(Status.FAIL, "Watchlist creation failed");
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
