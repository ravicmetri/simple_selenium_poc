package com.example.poc;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	protected WebDriver driver;

	@BeforeMethod
	public void setUp() {
		String chromeMajor = System.getenv("CHROME_MAJOR"); // set by workflow
		if (chromeMajor != null && !chromeMajor.isBlank()) {
			WebDriverManager.chromedriver().browserVersion(chromeMajor).setup();
		} else {
			// fallback to default (may pick latest)
			WebDriverManager.chromedriver().setup();
		}

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--window-size=1600,1200");
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-sync");
		options.addArguments("--disable-background-networking");

		String runHeaded = System.getenv("RUN_HEADED");
		String runHeadless = System.getenv("RUN_HEADLESS");
		if (runHeadless != null && (runHeadless.equalsIgnoreCase("true") || runHeadless.equals("1"))) {
			options.addArguments("--headless=new");
		} else if (runHeaded != null && (runHeaded.equalsIgnoreCase("true") || runHeaded.equals("1"))) {
			// headed
		} else {
			if ("true".equalsIgnoreCase(System.getenv("CI"))) {
				options.addArguments("--headless=new");
			}
		}

		// Optional verbose chromedriver logging
		Path logPath = Paths.get("target", "chromedriver.log");
		ChromeDriverService service = new ChromeDriverService.Builder().withVerbose(true).withSilent(false)
				.withLogFile(logPath.toFile()).build();

		driver = new ChromeDriver(service, options);

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
