package com.alramz.qa.utilities;

import com.alramz.qa.base.TestBase;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class TapByCoordinates extends TestBase{
	
	@SuppressWarnings("deprecation")
	public void pressByCoordinates(int x, int y) {

		TouchAction touchAction = new TouchAction(androiddriver);
		touchAction.tap(PointOption.point(x, y)).perform();
	}

}
