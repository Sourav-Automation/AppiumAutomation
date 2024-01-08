package com.alramz.qa.utilities;

import org.openqa.selenium.WebElement;

public class SplitUtil {

	public String splitamount(WebElement element) {
		
		String amount = element.getText();
		System.out.println(amount);
		String[] parts = amount.split(" ");
		String part2 = parts[0];
		System.out.println(part2);
		part2.trim();
		return part2;
		
	}
		



}
