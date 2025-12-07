package com.example.poc;

import org.testng.annotations.Test;

import io.qameta.allure.Allure;

public class SimpleTest extends BaseTest {

	@Test
	public void openDummyUrl() throws InterruptedException {

		driver.get("https://practicetestautomation.com/practice-test-login/");
		Allure.step("Open page");
		// test actions...
		// Allure.addAttachment("screenshot", "image/png", inputStreamOfScreenshot,
		// "png");
		String title = driver.getTitle();
		Thread.sleep(5000);
		// assertTrue(title.toLowerCase().contains("example"));
	}
}
