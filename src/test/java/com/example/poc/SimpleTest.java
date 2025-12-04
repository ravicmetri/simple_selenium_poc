package com.example.poc;

import org.testng.annotations.Test;

public class SimpleTest extends BaseTest {

	@Test
	public void openDummyUrl() throws InterruptedException {
		driver.get("https://practicetestautomation.com/practice-test-login/");
		String title = driver.getTitle();
		Thread.sleep(5000);
		// assertTrue(title.toLowerCase().contains("example"));
	}
}
