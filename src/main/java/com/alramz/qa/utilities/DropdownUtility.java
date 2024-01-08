package com.alramz.qa.utilities;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.alramz.qa.base.TestBase;
import com.aventstack.extentreports.Status;

public class DropdownUtility extends TestBase{
	
	public WebDriver driver;

	public DropdownUtility(WebDriver driver) {

		this.driver = driver;
	}
	
	public void selectDropdown(WebElement dropDownWebElement, String dropDownValue) throws InterruptedException {

		try {

			Actions actions = new Actions(driver);
			actions.moveToElement(dropDownWebElement).click();
			actions.sendKeys(dropDownValue).build().perform();
			Thread.sleep(4000);
			actions.sendKeys(Keys.ENTER).build().perform();

		} catch (Exception e) {
			test.log(Status.FAIL, "Exception :" + e);
		}

	}
	
	public void selectDocumentDropdown(WebElement dropDownWebElement, String dropDownValue)
			throws InterruptedException {

		try {

			Actions actions = new Actions(driver);
			actions.moveToElement(dropDownWebElement).click();
			actions.sendKeys(dropDownValue).build().perform();
			Thread.sleep(4000);
			actions.sendKeys(Keys.DOWN).build().perform();
			actions.sendKeys(Keys.DOWN).build().perform();
			actions.sendKeys(Keys.ENTER).build().perform();

		} catch (Exception e) {
			test.log(Status.FAIL, "Exception :" + e);
		}

	}

	public void selectStaticDropdown(WebElement dropDownWebElement) throws InterruptedException {

		try {

			Actions actions = new Actions(driver);
			actions.moveToElement(dropDownWebElement).click();
			actions.sendKeys(Keys.ENTER).build().perform();

		} catch (Exception e) {
			test.log(Status.FAIL, "Exception :" + e);
		}

	}

	public void selectUsingValue(WebElement element, String value) {

		try {
			Select dropdown = new Select(element);
			dropdown.selectByValue(value);

		} catch (Exception e) {
			test.log(Status.FAIL, "Exception :" + e);
		}

	}

	public void selectUsingIndex(WebElement element, int index) {

		try {
			Select dropdown = new Select(element);
			dropdown.selectByIndex(index);
		} catch (Exception e) {
			test.log(Status.FAIL, "Exception :" + e);
		}

	}

	public void selectUsingVisibleText(WebElement element, String value) {

		try {
			Select dropdown = new Select(element);
			dropdown.selectByVisibleText(value);
		} catch (Exception e) {
			test.log(Status.FAIL, "Exception :" + e);
		}

	}

	public List<String> selectAllOption(WebElement element) {

		Select select = new Select(element);
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<String>();
		for (WebElement ele : elementList) {
			valueList.add(ele.getText());

		}

		return valueList;
	}

}
