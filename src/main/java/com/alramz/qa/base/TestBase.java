package com.alramz.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver; // Used for Selenium web library
	public static AndroidDriver androiddriver; // Used for Appium Android library
	public static Properties prop; // Used for property File
	public static FileInputStream ip; // Used for file
	public static ExtentTest test; // Used for Extent Report
	public static ExtentReports extent; // Used for Extent Report
	public static ExtentSparkReporter reporter; // Used for Extent Report

	@BeforeSuite
	public static ExtentReports setUpSuite() {

		String path = System.getProperty("user.dir") + "/test-output/Alramz-AutomationTestResults.html";
		reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Automation Test Results");
		reporter.config().setDocumentTitle("TestResults");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Automation", "Tester");

		return extent;

	}

	// Below Method will invoke the driver according to config.properties
	// Always need to use initialization before running the testcases
	public static void initialization(String configpath) throws Exception {

		// fetching config.properties file from the project path
		try {
			ip = new FileInputStream(System.getProperty("user.dir") + configpath); // will get the config.properties
																					// path
			prop = new Properties(); // initialize property file
			prop.load(ip); // loading all the details of property file
			System.out.println("New Testcase Started Successfully");
			System.out.println("OS Name: " + System.getProperty("os.name") + "\n" + "Browser Name: "
					+ prop.getProperty("browser") + "\n" + "App Platform: " + prop.getProperty("platform") + "\n");

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Getting OS Name and storing in a variable eg: WINDOWS , MAC
		String OS = System.getProperty("os.name");
		// will fetch platform name from config file eg: WEB, ANDROID, IOS
		String platform = prop.getProperty("platform");

		// Code according to OS - WINDOWS, MAC
		if (OS.toUpperCase().contains("WINDOWS")) {

			// Checking Browser name from Maven Cammand, if True take System value,
			// if false take property file value
			String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
					: prop.getProperty("browser");

			// Code according to Browser - Chrome, Firefox and Android
			if (browserName.toUpperCase().contains("CHROME") && platform.toUpperCase().contains("WEB")) {
				WebDriverManager.chromedriver().setup(); // will check for latest driver available

				ChromeOptions options = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting.popups", 0);
				// prefs.put("download.default_directory", Path); // need to provide path for
				// Downloaded files
				options.setExperimentalOption("prefs", prefs);

				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setCapability(ChromeOptions.CAPABILITY, options);
				options.merge(cap);

				driver = new ChromeDriver(options); // initializing Chrome Driver

				driver.manage().window().maximize(); // Maximize Chrome Window
				driver.manage().deleteAllCookies(); // Delete all cache and cookies before starting session
				driver.get(prop.getProperty("weburl")); // Loading web Url fetched from property file

			} else if (browserName.toUpperCase().contains("FIREFOX") && platform.toUpperCase().contains("WEB")) {

				WebDriverManager.firefoxdriver().setup();
				FirefoxProfile options = new FirefoxProfile();
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting.popups", 0);
				options.setPreference("profile.default_content_setting.popups", 0);
				// options.setPreference("download.default_directory", Path);

				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);

//				driver = new FirefoxDriver(cap);

				driver.manage().window().maximize(); // Maximize Firefox Window
				driver.manage().deleteAllCookies(); // Delete all cache and cookies before starting session
				driver.get(prop.getProperty("weburl")); // Loading web Url fetched from property file

			} else if (platform.toUpperCase().contains("ANDROID")) {

				// ClassLoader classloader = getClass().getClassLoader();
				// File file = new File(classloader.getResource("--APK PATH--").getFile());
				// String apkPath = file.getAbsolutePath();

				// Adding Desired Options for android platform
				
				String runLocal = prop.getProperty("runLocal");
				if(runLocal.toUpperCase().contains("YES")) {
					
				UiAutomator2Options options = new UiAutomator2Options();
				options.setPlatformName(prop.getProperty("platformName"))
					   .setPlatformVersion(prop.getProperty("platformVersion"))
					   .setAutomationName(prop.getProperty("automationName"))
					   .setDeviceName(prop.getProperty("deviceName"))
					   .setAppPackage(prop.getProperty("appPackage"))
					   .setAppActivity(prop.getProperty("appActivity"))
					// .setApp(prop.getProperty("path"))
					   .setUdid(prop.getProperty("deviceUdid"))
					   .setNoReset(false);
				
				URL url = new URL("http://127.0.0.1:4723/"); // adding url for appium server
				androiddriver = new AndroidDriver(url, options); // initializing android driver 
				}else {

				String userName = System.getenv("BROWSERSTACK_USERNAME");
				String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
				String browserstackLocal = System.getenv("BROWSERSTACK_LOCAL");
				String browserstackLocalIdentifier = System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER");
				String app = System.getenv("BROWSERSTACK_APP_ID");

				DesiredCapabilities caps = new DesiredCapabilities();
				caps.setCapability("device", "Samsung Galaxy S8");
				caps.setCapability("app", app);
				caps.setCapability("browserstack.local", browserstackLocal);
				caps.setCapability("browserstack.localIdentifier", browserstackLocalIdentifier);

				URL url = new URL("//https://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub"); // https://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub
				androiddriver = new AndroidDriver(url, caps);
				}

			}

		} else if (OS.toUpperCase().contains("MAC")) {

			// Checking Browser name from Maven Cammand, if True take System value, if false
			// take property file value
			String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
					: prop.getProperty("browser");

			if (browserName.toUpperCase().contains("CHROME")) {
				WebDriverManager.chromedriver().setup();

				ChromeOptions options = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting.popups", 0);
				// prefs.put("download.default_directory", Path);
				options.setExperimentalOption("prefs", prefs);
				// DesiredCapabilities cap = DesiredCapabilities.chrome();
				// cap.setCapability(ChromeOptions.CAPABILITY, options);
				driver = new ChromeDriver();

			} else if (browserName.toUpperCase().contains("FF") || browserName.toUpperCase().contains("FIREFOX")) {

				WebDriverManager.firefoxdriver().setup();
				FirefoxProfile options = new FirefoxProfile();

				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting.popups", 0);
				// prefs.put("download.default_directory", Path);

				options.setPreference("profile.default_content_setting.popups", 0);
				// options.setPreference("download.default_directory", Path);

				// DesiredCapabilities cap = DesiredCapabilities.firefox();
				// cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
				// driver = new FirefoxDriver(cap);
			}

		}

	}

}
