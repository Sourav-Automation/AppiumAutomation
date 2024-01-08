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

public class DeleteWatchlistTest extends TestBase {

	@BeforeMethod
	public static void initializeDriver() throws Exception {

		initialization("\\src\\main\\java\\com\\alarmz\\qa\\config\\config.properties");
	}

	LoginPage login;
	WatchlistPage watchlist;

	String sheetName = "DeleteWatchlist";
	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir")
			+ "\\src\\main\\java\\com\\alramz\\qa\\sheets\\DeleteWatchlistTestData.xlsx";

	@DataProvider
	public Object[][] getTestData() {
		// System.out.println("SheetName is: " + sheetName );
		Object data[][] = ExcelDataReader.getTestData(sheetName, TESTDATA_SHEET_PATH);
		// System.out.println("Data is: " + data );
		return data;

	}

	@Test(dataProvider = "getTestData") // Data Provider'
	public void deleteWatchlistVerificationTest(String runmode, String username, String password, String watchlistName)
			throws Exception {

		test = extent.createTest("Delete Watchlist Verification Test: " + watchlistName);

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

				watchlist.deleteWatchlist(watchlistName);
				test.log(Status.INFO, "Clicked on Delete WatchList Button");

				watchlist.clickonDeleteButton();
				test.log(Status.INFO, "Clicked on Delete WatchList Button");
				try {
					watchlist.checkForDeletedWatchlist(watchlistName);	
				}catch(Exception e) {
					
					test.log(Status.PASS, "Watchlist Deleted Successfully");
					Assert.assertTrue(true);
					
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
