package com.alramz.qa.utilities;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;


import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.alramz.qa.base.TestBase;
import io.appium.java_client.android.AndroidDriver;

public class MobileWaitUtility extends TestBase {

	public AndroidDriver androiddriver;

	public MobileWaitUtility(AndroidDriver androiddriver) {

		this.androiddriver = androiddriver;
	}

	public void setImplicitWait(long timeout) {

		androiddriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
	}

	public WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) {

		WebDriverWait wait = new WebDriverWait(androiddriver, Duration.ofSeconds(timeOutInSeconds));
		wait.pollingEvery(Duration.ofMillis(pollingEveryInMiliSec));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}

	public void waitForElementVisibleWithPollingTime(WebElement element, int timeOutInSeconds,
			int pollingEveryInMilliSeconds) {
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMilliSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void waitForElement(WebElement element, long timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(androiddriver, Duration.ofSeconds(timeOutInSeconds));
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void waitForElementList(List<WebElement> element, long timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(androiddriver, Duration.ofSeconds(timeOutInSeconds));
		wait.until(ExpectedConditions.visibilityOf((WebElement) element));

	}

	public boolean waitForElementNotPresent(WebElement element, long timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(androiddriver, Duration.ofSeconds(timeOutInSeconds));
		boolean status = wait.until(ExpectedConditions.invisibilityOf(element));
		return status;

	}

	public void waitForElementtobeClickable(WebElement element, long timeOutInSeconds) {
		WebDriverWait w = new WebDriverWait(androiddriver, Duration.ofSeconds(timeOutInSeconds));
		w.until(ExpectedConditions.visibilityOf(element));
	}

}
