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

public class RenameWatchlistTest extends TestBase {

	@BeforeMethod
	public static void initializeDriver() throws Exception {

		initialization("\\src\\main\\java\\com\\alarmz\\qa\\config\\config.properties");
	}

	LoginPage login;
	WatchlistPage watchlist;

	String sheetName = "RenameWatchlist";
	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir")
			+ "\\src\\main\\java\\com\\alramz\\qa\\sheets\\RenameWatchlistTestData.xlsx";

	@DataProvider
	public Object[][] getTestData() {
		// System.out.println("SheetName is: " + sheetName );
		Object data[][] = ExcelDataReader.getTestData(sheetName, TESTDATA_SHEET_PATH);
		// System.out.println("Data is: " + data );
		return data;

	}

	@Test(dataProvider = "getTestData") // Data Provider'
	public void renameWatchlistVerificationTest(String runmode, String username, String password, String watchlistName,
			String newWatchlistName) throws Exception {

		test = extent.createTest("Rename Watchlist Verification Test: " + watchlistName);

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

				login.selectBiometricNo();
				test.log(Status.INFO, "Clicked on Biometric No Button");

				watchlist.tapByCoordinates(170, 362);
				test.log(Status.INFO, "Clicked on WatchList Button");
				Thread.sleep(3000);

				watchlist.editWatchlist(watchlistName);
				test.log(Status.INFO, "Clicked on Edit WatchList Button");

				watchlist.clickonEditWatchlist(watchlistName);
				test.log(Status.INFO, "Changing Watchlist Name: " + watchlistName);

				watchlist.enterNewWatchlistName(newWatchlistName);
				test.log(Status.INFO, "Entered New Watchlist Name: " + newWatchlistName);

				watchlist.clickonSaveButton();
				test.log(Status.INFO, "Clicked on Save Button");

				if (watchlist.checkforRenamedWatchlist(newWatchlistName) == true) {

					test.log(Status.PASS, "Watchlist Renamed Successfully");
					Assert.assertTrue(true);

				} else {

					test.log(Status.FAIL, "Failed to rename Watchlist");
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
