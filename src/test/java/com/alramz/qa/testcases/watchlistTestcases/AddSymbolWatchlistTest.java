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

public class AddSymbolWatchlistTest extends TestBase {
 //Hello
	@BeforeMethod
	public static void initializeDriver() throws Exception {

		initialization("\\src\\main\\java\\com\\alarmz\\qa\\config\\config.properties");
	}

	LoginPage login;
	WatchlistPage watchlist;

	String sheetName = "AddSymbol";
	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir")
			+ "\\src\\main\\java\\com\\alramz\\qa\\sheets\\AddWatchlistSymbolTestData.xlsx";

	@DataProvider
	public Object[][] getTestData() {
		// System.out.println("SheetName is: " + sheetName );
		Object data[][] = ExcelDataReader.getTestData(sheetName, TESTDATA_SHEET_PATH);
		// System.out.println("Data is: " + data );
		return data;

	}

	@Test(dataProvider = "getTestData") // Data Provider'
	public void addnewWatchlistSymbolVerificationTest(String runmode, String username, String password,
			String symbolName) throws Exception {

		test = extent.createTest("Add New Watchlist Symbol Verification Test");

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
				test.log(Status.INFO, "Selected Biometric as No");

				if (watchlist.getSuccessMessage().contains("No symbols found")) {

					watchlist.clickonAddSymbolButton();
					test.log(Status.INFO, "Clicked on Add Symbol Button");

				} else {

					watchlist.clickonAddAnotherSymbolButton();
					test.log(Status.INFO, "Clicked on Add Symbol Button");
				}

				watchlist.searchSymbol(symbolName);
				test.log(Status.INFO, "Searched for Symbol Name: " + symbolName);

				watchlist.tapByCoordinates(908, 392);
				test.log(Status.INFO, "Clicked on Add Symbol");
				Thread.sleep(3000);

				if (watchlist.checkWatchlistforSymbol(symbolName).contains(symbolName)) {

					test.log(Status.PASS, "Symbol " + symbolName + " Added Successfully");
					Assert.assertTrue(true);

				} else {

					test.log(Status.FAIL, "Symbol " + symbolName + " Failed to add");
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
