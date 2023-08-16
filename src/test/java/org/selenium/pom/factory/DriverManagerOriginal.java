package org.selenium.pom.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.selenium.pom.constants.DriverType;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManagerOriginal {
	
	public WebDriver initializeDriver(String browser) {
		WebDriver driver;
		switch(DriverType.valueOf(browser)) {
		case CHROME: //from the enum constant
			WebDriverManager.chromedriver().cachePath("Drivers").setup();
			driver = new ChromeDriver();
			break;
		case FIREFOX:
			WebDriverManager.firefoxdriver().cachePath("Drivers").setup();
			driver = new FirefoxDriver();
			break;
			default:
				throw new IllegalStateException("Invalid browser name: " + browser);
		}

		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
}
