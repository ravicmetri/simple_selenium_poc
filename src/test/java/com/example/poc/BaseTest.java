package com.example.poc;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
	protected WebDriver driver;

	@BeforeMethod
	public void setUp() {
		ChromeOptions options = new ChromeOptions();
		// add your options...
		driver = new ChromeDriver(options);

		// options.addArguments("--headless=new");
		// options.addArguments("--no-sandbox");
		// options.addArguments("--disable-dev-shm-usage");
		driver = new ChromeDriver(options);
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null)
			driver.quit();
	}
}
