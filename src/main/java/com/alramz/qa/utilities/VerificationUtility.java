package com.alramz.qa.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.alramz.qa.base.TestBase;
import com.aventstack.extentreports.Status;

public class VerificationUtility extends TestBase {

	public WebDriver driver;

	public VerificationUtility(WebDriver driver) {

		this.driver = driver;
	}

	public boolean isDisplayed(WebElement element) {

		try {

			element.isDisplayed();
			return true;
		} catch (Exception e) {
			test.log(Status.FAIL, "Exception :" + e);
		}
		return false;

	}

	public boolean isNotDisplayed(WebElement element) {

		try {

			element.isDisplayed();
			return false;
		} catch (Exception e)

		{
			test.log(Status.FAIL, "Exception :" + e);
		}

		return true;

	}

	public String getText(WebElement element) {
		if (null == element) {
			return null;

		}
		boolean status = isDisplayed(element);
		if (status) {
			return element.getText();
		} else {
			return null;
		}

	}
	

}
