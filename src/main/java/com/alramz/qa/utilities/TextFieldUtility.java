package com.alramz.qa.utilities;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;



public class TextFieldUtility {
	
	
	public WebDriver driver;
	
	public TextFieldUtility(WebDriver driver) {
		
		this.driver=driver;				
	}
	
	
	
	
public void enterNumberToTextField(WebElement textFieldWebElement, double textFieldValue) throws InterruptedException {
		
				
		Actions actions = new Actions(driver);
		actions.moveToElement(textFieldWebElement).click().build().perform();
		actions.sendKeys(String.valueOf(textFieldValue)).build().perform();
		actions.sendKeys(Keys.ENTER).build().perform();
		}


}