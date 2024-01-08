package com.alramz.qa.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.asserts.SoftAssert;
import org.testng.asserts.SoftAssert;

import com.alramz.qa.base.TestBase;
import com.aventstack.extentreports.Status;

public class TestUtil extends TestBase {

	public static long PAGE_LOAD_TIMEOUT = 60;
	public static int IMPLICIT_WAIT = 60;
	static JavascriptExecutor js;

	public void switchToFrame() {
		driver.switchTo().frame("mainpanel");
	}

	public static void runTimeInfo(String messageType, String message) throws InterruptedException {
		js = (JavascriptExecutor) driver;
		// Check for jQuery on the page, add it if need be
		js.executeScript("if (!window.jQuery) {"
				+ "var jquery = document.createElement('script'); jquery.type = 'text/javascript';"
				+ "jquery.src = 'https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js';"
				+ "document.getElementsByTagName('head')[0].appendChild(jquery);" + "}");
		Thread.sleep(5000);

		// Use jQuery to add jquery-growl to the page
		js.executeScript("$.getScript('https://the-internet.herokuapp.com/js/vendor/jquery.growl.js')");

		// Use jQuery to add jquery-growl styles to the page
		js.executeScript("$('head').append('<link rel=\"stylesheet\" "
				+ "href=\"https://the-internet.herokuapp.com/css/jquery.growl.css\" " + "type=\"text/css\" />');");
		Thread.sleep(5000);

		// jquery-growl w/ no frills
		js.executeScript("$.growl({ title: 'GET', message: '/' });");
//'"+color+"'"
		if (messageType.equals("error")) {
			js.executeScript("$.growl.error({ title: 'ERROR', message: '" + message + "' });");
		} else if (messageType.equals("info")) {
			js.executeScript("$.growl.notice({ title: 'Notice', message: 'your notice message goes here' });");
		} else if (messageType.equals("warning")) {
			js.executeScript("$.growl.warning({ title: 'Warning!', message: 'your warning message goes here' });");
		} else
			System.out.println("no error message");
		// jquery-growl w/ colorized output
//		js.executeScript("$.growl.error({ title: 'ERROR', message: 'your error message goes here' });");
//		js.executeScript("$.growl.notice({ title: 'Notice', message: 'your notice message goes here' });");
//		js.executeScript("$.growl.warning({ title: 'Warning!', message: 'your warning message goes here' });");
		Thread.sleep(5000);
	}



	//Description: Method to wait till any object exists

	public static WebElement waitForAnyObject(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(IMPLICIT_WAIT));
		return wait.until(ExpectedConditions.visibilityOf(element));

	}


	 // Description: Method to click any object

	public static WebElement clickAnyObject(WebElement e) {
		try {
			waitForAnyObject(e);
			e.click();

		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
		}
		return e;
	}

	@SuppressWarnings("unused")
	public static void selectDropDownValues(WebElement dropDownWebElement, String inputValue)
			throws InterruptedException {
		boolean isDropDownValueDisplay = false;

		do {
			TestUtil.onClick(driver, dropDownWebElement, 10);
			Actions actions = new Actions(driver);
			actions.moveToElement(dropDownWebElement).perform();
			actions.click().perform();
			actions.sendKeys(Keys.CLEAR).build().perform();
			actions.sendKeys(inputValue).build().perform();
			actions.sendKeys(Keys.ENTER).build().perform();

			// driver.findElement(By.xpath("//div[contains(@id,'react-select') and
			// text()='"+inputValue+"']")).click();

			// isDropDownValueDisplay =
			// driver.findElement(By.xpath("//div[contains(@id,'react-select') and
			// text()='"+inputValue+"']")).isDisplayed();
			String value = driver.findElement(By.xpath("//span[text()='" + inputValue + "']"))
					.getAttribute("innerHTML");
			System.out.println("Drop down value" + value);
		} while (isDropDownValueDisplay = false);
	}

	public static void selectDropDownCurrencyValues(WebElement dropDownWebElement, String inputValue)
			throws InterruptedException {

//		JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("document.getElementByXPath('//label[contains(text(),'Currency')]/following::div[1]').setAttribute('value',
		// 'inputValue');");

		// js.executeScript("arguments[0].setAttribute('value', 'arguments[1]')",
		// dropDownWebElement, inputValue);

		// boolean isDropDownValueDisplay = false;

		// do {
		TestUtil.onClick(driver, dropDownWebElement, 10);
		Actions actions = new Actions(driver);
		actions.moveToElement(dropDownWebElement).perform();
		actions.click().perform();

		for (int i = 0; i < inputValue.length(); i++) {
			char c = inputValue.charAt(i);
			String s = new StringBuilder().append(c).toString();
			System.out.println("Drop down value" + s);
			actions.sendKeys(s);
		}

		// actions.sendKeys(Keys.CLEAR).perform();
		// actions.sendKeys(dropDownWebElement,inputValue).perform();

		// actions.sendKeys(Keys.chord(inputValue));
		// Thread.sleep(5000);
		// actions.sendKeys(Keys.ENTER).perform();
		// driver.findElement(By.xpath("//div[contains(@id,'react-select') and
		// text()='"+inputValue+"']")).click();

		// driver.findElement(By.xpath("//div[contains(@id,'react-select') and
		// text()='"+inputValue+"']")).click();

		// isDropDownValueDisplay=
		// driver.findElement(By.xpath("//div[contains(@id,'react-select') and
		// text()='"+inputValue+"']")).isDisplayed();
		// isDropDownValueDisplay =
		// driver.findElement(By.xpath("//div[contains(@id,'react-select') and
		// text()='"+inputValue+"']")).isDisplayed();
		// String
		// value=driver.findElement(By.xpath("//span[text()='"+inputValue+"']")).getAttribute("innerHTML");
		System.out.println("Drop down value");
		// } while (isDropDownValueDisplay = false);
	}

	@SuppressWarnings("unused")
	public static void selectBankDropDownValues(WebElement dropDownWebElement, String inputValue)
			throws InterruptedException {
		boolean isDropDownValueDisplay = false;

		do {

			Actions actions = new Actions(driver);
			actions.moveToElement(dropDownWebElement).perform();
			actions.click().perform();
			actions.sendKeys(inputValue).build().perform();
			driver.findElement(By.xpath("//*[@id='react-select-5--option-0']")).click();
			isDropDownValueDisplay = driver.findElement(By.xpath("//span[text()='" + inputValue + "']")).isDisplayed();
			String value = driver.findElement(By.xpath("//span[text()='" + inputValue + "']"))
					.getAttribute("innerHTML");
			System.out.println("Drop down value" + value);
		} while (isDropDownValueDisplay = false);
	}

	public static void selectAccountTypeDropDownValues(WebElement dropDownWebElement, String inputValue)
			throws InterruptedException {
		@SuppressWarnings("unused")
		boolean isDropDownValueDisplay = false;

		do {

			Actions actions = new Actions(driver);
			actions.moveToElement(dropDownWebElement).perform();
			actions.click().perform();

			actions.sendKeys(inputValue).build().perform();
			actions.sendKeys(Keys.RETURN).build().perform();

			isDropDownValueDisplay = driver.findElement(By.xpath("//span[text()='" + inputValue + "']")).isDisplayed();
			String value = driver.findElement(By.xpath("//span[text()='" + inputValue + "']"))
					.getAttribute("innerHTML");
			System.out.println("Drop down value:" + value);
		} while (isDropDownValueDisplay = false);
	}

	@SuppressWarnings("deprecation")
	public static Boolean waitForAnyObjectTillNotVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(IMPLICIT_WAIT));
		return wait.until(ExpectedConditions.invisibilityOf(element));

	}

	public static void sendKeys(WebDriver driver, WebElement element, int timeout, String inputValue) {
		new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(inputValue);

	}


	public static void onClick(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(element));
		element.click();

	}

	public static void waitForloader(WebDriver driver, WebElement element, int timeout) {

		new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.invisibilityOf(element));

	}

	// TestNG Assertion
	public static void validateFieldTextNotEmpty(String fieldValues) {

		SoftAssert softassert = new SoftAssert();
		softassert.assertTrue(!fieldValues.isEmpty(), "Value is Empty");
		softassert.assertAll();
	}

	public static void validateString(String expectedResults, String actualResults) {

		SoftAssert softassert = new SoftAssert();
		test.log(Status.INFO, "Validation");
		softassert.assertTrue(expectedResults.equalsIgnoreCase(actualResults), "Test-Case Failed-Mismatched Found");
		softassert.assertAll();
	}

	public static void validateValue(String expectedResults, String actualResults) {

		SoftAssert softassert = new SoftAssert();
		test.log(Status.INFO, "Validation");
		softassert.assertTrue(expectedResults.contains(actualResults), "Test-Case Failed-Mismatched Found");
		softassert.assertAll();
	}

	public static String getCurrentDateTime() {

		DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentDate = new Date();
		customFormat.format(currentDate);
		return customFormat.format(currentDate);
	}

	public static String captureScreenshot(WebDriver driver)

	{

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotPath = System.getProperty("user.dir") + "/screenshots/AMX_" + getCurrentDateTime() + ".png";

		try {
			FileHandler.copy(src, new File(screenshotPath));
			System.out.println("Screenshot captured ");
		} catch (IOException e) {
			System.out.println("Unable to capture screenshot " + e.getMessage());

		}

		return screenshotPath;

	}

	public static void takeScreenshotAtEndOfTest() throws IOException {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}

}
