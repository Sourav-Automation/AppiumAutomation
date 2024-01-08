package com.alramz.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.alramz.qa.base.TestBase;
import com.alramz.qa.utilities.MobileWaitUtility;
import com.alramz.qa.utilities.TapByCoordinates;
import com.alramz.qa.utilities.TimeUtil;

import io.appium.java_client.AppiumBy;

public class WatchlistPage extends TestBase {

	TimeUtil timeUtility;
	MobileWaitUtility waitUtility;
	TapByCoordinates tap;

	// Page Factory

	@FindBy(xpath = "//android.view.View[@content-desc=\"Yes\"]")
	WebElement biometricYes;

	@FindBy(xpath = "//android.view.View[@content-desc=\"No\"]")
	WebElement biometricNo;

	@FindBy(xpath = "//android.widget.Button[@content-desc=\"ADD SYMBOL\"]")
	WebElement addSymbolButton;

	@FindBy(xpath = "//android.widget.Button[@content-desc=\"ADD ANOTHER SYMBOL\"]")
	WebElement addAnotherSymbolButton;

	@FindBy(xpath = "//android.widget.EditText")
	WebElement enterWatchlistName;

	@FindBy(xpath = "//android.widget.Button[@content-desc=\"CREATE\"]")
	WebElement createButton;

	@FindBy(xpath = "//android.widget.EditText")
	WebElement searchSymbol;

	@FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View")
	WebElement androidView;

	@FindBy(xpath = "//android.view.View[@content-desc=\"EMAAR\r\n" + "DFM\r\n" + "EMAAR PROPERTIES\r\n" + "ADD\"]")
	WebElement popularSymbol; // 537,908

	@FindBy(xpath = "//android.view.View[@content-desc=\"new watchlist\"]/android.widget.ImageView[3]")
	WebElement deleteWatchlist;

	@FindBy(xpath = "//android.widget.Button[@content-desc=\"DELETE\"]")
	WebElement deleteButton;

	@FindBy(xpath = "//android.widget.EditText[@text=\"My Watchlist\"]")
	WebElement editWatchlistText;

	@FindBy(className = "android.widget.EditText")
	WebElement enterNewWatchlistName;

	@FindBy(xpath = "//android.view.View[@content-desc=\"SAVE\"]")
	WebElement saveButton;

	public WatchlistPage() {
		PageFactory.initElements(androiddriver, this);
		timeUtility = new TimeUtil();
		waitUtility = new MobileWaitUtility(androiddriver);
		tap = new TapByCoordinates();
	}

	public void selectBiometricNo() {

		waitUtility.waitForElement(biometricNo, 40);
		biometricNo.click();
	}

	public void tapByCoordinates(int x, int y) {

		tap.pressByCoordinates(x, y);
	}

	public void clickonWatchlistName() {

		enterWatchlistName.click();
	}

	public void clickonAddSymbolButton() {

		addSymbolButton.click();
	}

	public void clickonAddAnotherSymbolButton() {

		addAnotherSymbolButton.click();
	}

	public void enterWatchlistName(String name) {

		enterWatchlistName.sendKeys(name);

	}

	public void clickonCreateButton() {

		createButton.click();

	}

	public void searchSymbol(String name) {

		searchSymbol.click();
		searchSymbol.sendKeys(name);
	}

	public String getSuccessMessage() {

		String message = androidView.getAttribute("content-desc");
		// System.out.println(message);
		return message;
	}

	public void deleteWatchlist(String watchlistName) {

		androiddriver
				.findElement(AppiumBy.xpath(
						"//android.view.View[@content-desc='" + watchlistName + "']/android.widget.ImageView[2]"))
				.click();

	}

	public void editWatchlist(String watchlistName) {

		androiddriver
				.findElement(AppiumBy.xpath(
						"//android.view.View[@content-desc='" + watchlistName + "']/android.widget.ImageView[1]"))
				.click();

	}

	public void clickonDeleteButton() {

		waitUtility.waitForElement(deleteButton, 20);
		deleteButton.click();
	}

	public void clickonSaveButton() {
		saveButton.click();
	}

	public Boolean checkforRenamedWatchlist(String watchlistName) {

		WebElement watchlist = androiddriver.findElement(AppiumBy
				.xpath("//android.view.View[@content-desc='" + watchlistName + "']/android.widget.ImageView[1]"));

		Boolean isdisplayed = watchlist.isDisplayed();

		return isdisplayed;

	}

	public void clickonEditWatchlist(String watchlistName) {

		WebElement editButton = androiddriver
				.findElement(By.xpath("//android.widget.EditText[@text='" + watchlistName + "']"));

		editButton.click();
		editButton.clear();

	}

	public void enterNewWatchlistName(String newWatchlistName) {

		enterNewWatchlistName.sendKeys(newWatchlistName);
	}

	public void checkForDeletedWatchlist(String watchlistName) {

		WebElement verifynull = androiddriver
				.findElement(By.xpath("//android.view.View[@content-desc='" + watchlistName + "']"));

		verifynull.isDisplayed();
	}

	public String checkWatchlistforSymbol(String symbolName) {

		WebElement verifysymbol = androiddriver
				.findElement(By.xpath("//android.view.View[contains (@content-desc , '" + symbolName + "')]"));

		String msg = verifysymbol.getAttribute("content-desc");

		// System.out.println(msg);
		return msg;
	}

}
