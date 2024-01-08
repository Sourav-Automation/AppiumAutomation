package com.alramz.qa.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.alramz.qa.base.TestBase;

public class TakeScreenshot extends TestBase{
	
	public String getScreenshot(String screenshotName) throws IOException {

		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;

		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = null;
		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		try {
			destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
					+ ".png";
			File finalDestination = new File(destination);

			FileUtils.copyFile(source, finalDestination);
		} catch (IOException e) {

			e.printStackTrace();

		}

		return destination;
	}

}
