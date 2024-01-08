package com.alramz.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.alramz.qa.base.TestBase;
import com.alramz.qa.utilities.TimeUtil;

public class MyProfilePage extends TestBase {

	TimeUtil timeUtility;

	// Page Factory
	@FindBy(className = "android.widget.ImageView")
	WebElement profileIcon;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc='Logout']")
	WebElement logoutOption;

	@FindBy(xpath = "//android.widget.Button[@content-desc='Logout']")
	WebElement logoutButton;

	@FindBy(className = "android.widget.ImageView")
	WebElement alramzLogo;

	public MyProfilePage() {
		PageFactory.initElements(androiddriver, this);
		timeUtility = new TimeUtil();
	}

	public void clickonMyProfile() {

		profileIcon.click();
	}

	public void selectLogoutOption() {

		logoutOption.click();

	}
	
	public void clickonLogoutButton() {
		
		logoutButton.click();
	}
	
	public Boolean verifyAlramzLogo() {
		
		Boolean logo =  alramzLogo.isDisplayed();
		return logo;
	}

}
