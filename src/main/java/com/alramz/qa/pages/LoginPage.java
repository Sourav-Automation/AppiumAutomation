package com.alramz.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.alramz.qa.base.TestBase;
import com.alramz.qa.utilities.MobileWaitUtility;
import com.alramz.qa.utilities.TimeUtil;

public class LoginPage extends TestBase {

	TimeUtil timeUtility;
	MobileWaitUtility waitUtility;

	// Page Factory
	@FindBy(xpath = "//android.view.View[@content-desc=\"SKIP\"]")
	WebElement skipButton;

	@FindBy(className = "android.widget.EditText")
	WebElement enterUsername;

	@FindBy(xpath = "//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]")
	WebElement clickPassword;

	@FindBy(xpath = "//android.widget.ScrollView/android.widget.EditText[2]")
	WebElement enterPassword;

	@FindBy(xpath = "//android.widget.Button[@content-desc=\"SIGN IN\"]")
	WebElement signinButton;
	
	@FindBy(xpath = "//android.view.View[@content-desc=\"Yes\"]")
	WebElement biometricYes;
	
	@FindBy(xpath = "//android.view.View[@content-desc=\"No\"]")
	WebElement biometricNo;

	public LoginPage() {
		PageFactory.initElements(androiddriver, this);
		waitUtility = new MobileWaitUtility(androiddriver);
		timeUtility = new TimeUtil();
	}

	public void clickonSkipButton() {

		// timeUtility.longWait();
		waitUtility.waitForElement(skipButton, 50);
		skipButton.click();
	}

	public void enterUsername(String username) {

		enterUsername.click();
		enterUsername.sendKeys(username);
	}

	public void enterPassword(String password) throws InterruptedException {

		clickPassword.click();
		enterPassword.sendKeys(password);
	}

	public WatchlistPage clickonSigninButton() {

		signinButton.click();
		return new WatchlistPage();
	}

	public MyProfilePage clickSignup() {

		signinButton.click();
		return new MyProfilePage();
	}

	public void goBack() {

		androiddriver.navigate().back();
	}
	
	public void selectBiometricNo() {
		
		waitUtility.waitForElement(biometricNo, 40);
		biometricNo.click();
	}

}
