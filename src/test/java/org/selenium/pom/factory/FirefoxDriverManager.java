package org.selenium.pom.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirefoxDriverManager implements DriverManager {

	@Override
	public WebDriver createDriver() {
		WebDriverManager.firefoxdriver().cachePath("Drivers").setup();
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		return driver;
	}
}
