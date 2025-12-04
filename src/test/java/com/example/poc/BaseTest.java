package com.example.poc;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	protected WebDriver driver;

	@BeforeMethod
	public void setUp() {
		// Option 1: use WebDriverManager (recommended for local runs)
		WebDriverManager.chromedriver().setup();

		// Option 2 (alternatively): remove WebDriverManager.chromedriver().setup();
		// and let Selenium Manager (Selenium 4+) download matching driver
		// automatically:
		// driver = new ChromeDriver();

		ChromeOptions options = new ChromeOptions();

		// Common friendly options
		options.addArguments("--window-size=1600,1200");
		options.addArguments("--remote-allow-origins=*"); // avoid origin errors on some combos
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-sync");
		options.addArguments("--disable-background-networking");

		// Decide headed vs headless
		String runHeaded = System.getenv("RUN_HEADED");
		String runHeadless = System.getenv("RUN_HEADLESS");

		if (runHeadless != null && (runHeadless.equalsIgnoreCase("true") || runHeadless.equals("1"))) {
			options.addArguments("--headless=new");
		} else if (runHeaded != null && (runHeaded.equalsIgnoreCase("true") || runHeaded.equals("1"))) {
			// headed: nothing to add - Chrome will launch normally on Windows runner
		} else {
			// default: if CI=true and nothing else set, run headless; else headed
			if ("true".equalsIgnoreCase(System.getenv("CI"))) {
				options.addArguments("--headless=new");
			}
		}

		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
